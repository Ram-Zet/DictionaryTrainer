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

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return userRole.getAuthorities();
    }

}
