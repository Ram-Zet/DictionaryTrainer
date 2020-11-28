package ramzet89.dictionary.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.db.entity.UserEntity;
import ramzet89.dictionary.db.repository.ext.DictionaryRepositoryExt;

import java.util.Set;

public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Long>, DictionaryRepositoryExt {

    @Query("SELECT d.id FROM DictionaryEntity d WHERE d.user = :user AND d.attempts = 0")
    Set<Long> getNewWordsIds(UserEntity user);

    @Query("SELECT d.id FROM DictionaryEntity d WHERE d.user = :user AND d.attempts > 0")
    Set<Long> getAllRepeatWordsIds(UserEntity user);


}
