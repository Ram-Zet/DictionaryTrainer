package ramzet89.dictionary.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @Size(min = 2, max = 50)
    private String email;

    @ElementCollection(targetClass = UserRole.class)
    @JoinTable(schema = "application", name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> userRoles;

}
