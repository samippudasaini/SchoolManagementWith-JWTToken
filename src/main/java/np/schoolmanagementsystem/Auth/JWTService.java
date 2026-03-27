package np.schoolmanagementsystem.Auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import np.schoolmanagementsystem.Enum.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    // Use a secure 256-bit Base64 key (can generate with tools like openssl)
    private static final String SECRET_KEY = "zHbJXUdrFr/9YGz2nU6tYxSPL6Yoww9k9mjZj+9nT4E=";


    // Generate token with username and role
    public String generateToken(String username, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());
        long now = System.currentTimeMillis();
        long expirationTime = 1000L * 60 * 60 * 24 * 7; // 7 days

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(now))
                .expiration(new Date(now + expirationTime)) // 7 days
                .and()
                .signWith(getKey())
                .compact();
    }

    // Decode the Base64 secret key and return a SecretKey object
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract username (subject) from the token
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // General claim extractor
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claimsResolver.apply(claims) : null;
    }

    // Get all claims from token, safely
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired at: " + e.getClaims().getExpiration());
        } catch (JwtException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
        return null;
    }

    // Check if token is valid and belongs to the correct user
    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = extractAllClaims(token);
        if (claims == null) return false;

        String username = claims.getSubject();
        Date expiration = claims.getExpiration();

        return username.equals(userDetails.getUsername()) && expiration.after(new Date());
    }

    // Check if token has expired
    public boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration == null || expiration.before(new Date());
    }


}
