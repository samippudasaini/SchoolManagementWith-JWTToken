package np.schoolmanagementsystem.Auth;

import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.entity.Staff;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StaffPrincipal implements UserDetails {
    private Staff staff;

    public StaffPrincipal(Staff staff) {
        this.staff = staff;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + getRole()));
    }

    private Role getRole() {
        return staff.getRole();

    }

    @Override
    public String getPassword() {
        return staff.getPassword();
    }

    @Override
    public String getUsername() {
        return staff.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
