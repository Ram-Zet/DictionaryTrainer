package ramzet89.dictionary.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ramzet89.dictionary.db.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
