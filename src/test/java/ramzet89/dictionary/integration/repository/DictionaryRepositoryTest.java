package ramzet89.dictionary.integration.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.db.entity.UserEntity;
import ramzet89.dictionary.db.repository.DictionaryRepository;
import ramzet89.dictionary.db.repository.UserRepository;
import ramzet89.dictionary.integration.AbstractDbTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class DictionaryRepositoryTest extends AbstractDbTest {

    @Autowired
    DictionaryRepository repository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Sql(scripts = {"/scripts/user.sql", "/scripts/clean-dictionary.sql"})
    public void cleanRepository() {
        UserEntity userEntity = userRepository.getOne(1L);


        Set<Long> newWordsIds = repository.getNewWordsIds(userEntity);
        Set<Long> allRepeatWordsIds = repository.getAllRepeatWordsIds(userEntity);
        List<Long> difficultRepeatWordsIds = repository.getDifficultRepeatWordsIds(Collections.emptySet(), 4, userEntity);
        List<Long> oldestRepeatWordsIds = repository.getOldestRepeatWordsIds(Collections.emptySet(), 4, userEntity);

        assertEquals(newWordsIds.size(), 30);
        assertEquals(allRepeatWordsIds.size(), 0);
        assertEquals(difficultRepeatWordsIds.size(), 0);
        assertEquals(oldestRepeatWordsIds.size(), 0);
    }

    @Test
    @Sql(scripts = {"/scripts/user.sql", "/scripts/clean-dictionary.sql"})
    @Sql(scripts = {"/scripts/updated-dictionary.sql"})
    public void updatedRepository() {
        UserEntity userEntity = userRepository.getOne(1L);

        Set<Long> newWordsIds = repository.getNewWordsIds(userEntity);
        Set<Long> allRepeatWordsIds = repository.getAllRepeatWordsIds(userEntity);
        List<Long> difficultRepeatWordsIds = repository.getDifficultRepeatWordsIds(allRepeatWordsIds, 4, userEntity);
        List<Long> oldestRepeatWordsIds = repository.getOldestRepeatWordsIds(allRepeatWordsIds, 4, userEntity);

        assertEquals(newWordsIds.size(), 14);
        assertEquals(allRepeatWordsIds.size(), 16);
        assertEquals(difficultRepeatWordsIds.size(), 4);
        assertEquals(oldestRepeatWordsIds.size(), 4);

        List<DictionaryEntity> difficult = repository.findAllByIdAndUser(new HashSet<>(difficultRepeatWordsIds), userEntity);
        List<DictionaryEntity> oldest = repository.findAllByIdAndUser(new HashSet<>(oldestRepeatWordsIds), userEntity);

        assertTrue(difficult.stream()
                .allMatch(
                        word -> word.getDifficultyCoefficient().equals(0.5)));
        assertTrue(oldest.stream()
                .allMatch(
                        word -> word.getAttemptDate().equals(LocalDateTime.of(2021, 1, 1, 0, 0))));
    }
}
