package ramzet89.dictionary.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ramzet89.dictionary.enums.security.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@Table(schema = "application",  name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @Size(min = 5, max = 72)
    private String password;

    @NotNull
    @Size(min = 2, max = 50)
    private String email;

    private boolean enabled = false;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @JoinTable(schema = "application", name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> userRoles;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return userRoles.stream().flatMap(userRole -> userRole.getAuthorities().stream())
                .collect(Collectors.toSet());
    }

}
