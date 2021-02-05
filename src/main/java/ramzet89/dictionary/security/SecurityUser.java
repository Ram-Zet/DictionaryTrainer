package ramzet89.dictionary.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ramzet89.dictionary.db.entity.UserEntity;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final String email;
    private final Set<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    private SecurityUser(String username, String password, String email, Set<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static SecurityUser fromUserEntity(UserEntity userEntity) {
        return new SecurityUser(userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getAuthorities(),
                userEntity.isEnabled()
        );
    }
}
