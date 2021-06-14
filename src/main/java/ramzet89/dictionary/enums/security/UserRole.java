package ramzet89.dictionary.enums.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ramzet89.dictionary.enums.security.Permission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN(Set.of(ADMIN_USERS, USER_LEARN, USER_ADD_WORDS)),
    USER(Set.of(USER_ADD_WORDS, USER_LEARN));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
