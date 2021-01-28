package ramzet89.dictionary.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ramzet89.dictionary.db.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
