package ramzet89.dictionary.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ramzet89.dictionary.db.entity.DictionaryEntity;

public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Long> {
}
