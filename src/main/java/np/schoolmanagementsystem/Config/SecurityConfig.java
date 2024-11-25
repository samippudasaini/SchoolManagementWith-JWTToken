package np.schoolmanagementsystem.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
//
////        it is use to disable csrf
//        http.csrf(customizer->customizer.disable());
//
//// no one can access without authentication
//        http.authorizeRequests(request-> request.anyRequest().authenticated());
//
////        its allow login form to authentication
////        http.formLogin(Customizer.withDefaults());
//
////        its allow http basic for authentication
//        http.httpBasic(Customizer.withDefaults());
//// each time generated new session id use this method form cannot be access to login browser default give popPop
////        use this method to comment or remove http.formLogin
//
//        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//          return http.build();


        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("api/students/login")
                        .permitAll()
                        .requestMatchers("api/students/register")
                        .permitAll()
                        .requestMatchers("api/teacher/register", "api/fee/savefee", "api/fee/delete/{feeId}",
                                "api/fee/feeupdate/{feeId}","api/staff/add")
                        .permitAll()
                        .requestMatchers("api/teacher/login")
                        .permitAll()
                        .requestMatchers("api/staff/login")
                        .permitAll()
//                        .requestMatchers("api/fee/savefee")
//                        .permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }


//    @Bean
//    public UserDetailsService userDetailsService() {

////        It's for default self created or hardcoded
//
//
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("User")
//                .password("password")
//                .roles("USER")
//                .build();
//
//
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("User1")
//                .password("password1")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }


}
