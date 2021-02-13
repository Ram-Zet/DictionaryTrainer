package ramzet89.dictionary.integration.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ramzet89.dictionary.integration.AbstractDbTest;
import ramzet89.dictionary.model.commons.DictionaryRecord;
import ramzet89.dictionary.model.request.GetWordsToLearnRequest;
import ramzet89.dictionary.service.GetWordsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class GetWordsServiceTest extends AbstractDbTest {

    @Autowired
    private GetWordsService getWordsService;

    @Test
    @Sql(scripts = {"/scripts/user.sql", "/scripts/clean-dictionary.sql"})
    public void cleanDbTest() {
        GetWordsToLearnRequest request = GetWordsToLearnRequest.builder()
                .newWordsCount(5)
                .repeatWordsDifficultCount(2)
                .repeatWordsRandomCount(2)
                .repeatWordsElderCount(2)
                .build();


        List<DictionaryRecord> words = getWordsService.getWordsToLearns(request, 1L).getWords();
        assertEquals(words.size(), 5);
        words.stream().allMatch(word -> word.getAttempts() == 0);
    }

    @Test
    @Sql(scripts = {"/scripts/user.sql", "/scripts/clean-dictionary.sql"})
    public void cleanDbTestRequestIsZero() {
        GetWordsToLearnRequest request = GetWordsToLearnRequest.builder()
                .newWordsCount(5)
                .repeatWordsDifficultCount(0)
                .repeatWordsRandomCount(0)
                .repeatWordsElderCount(0)
                .build();


        List<DictionaryRecord> words = getWordsService.getWordsToLearns(request, 1L).getWords();
        assertEquals(words.size(), 5);
        words.stream().allMatch(word -> word.getAttempts() == 0);
    }

    @Test
    @Sql(scripts = {"/scripts/user.sql", "/scripts/clean-dictionary.sql"})
    @Sql(scripts = {"/scripts/updated-dictionary.sql"})
    public void updatedDbTest() {
        GetWordsToLearnRequest request;
        request = GetWordsToLearnRequest.builder()
                .newWordsCount(5)
                .repeatWordsDifficultCount(2)
                .repeatWordsRandomCount(2)
                .repeatWordsElderCount(2)
                .build();

        List<DictionaryRecord> words = getWordsService.getWordsToLearns(request, 1L).getWords();
        assertEquals(words.size(), 11);
        assertTrue(words.stream().filter(word -> (word.getFailures()*2) == 4).count() >=2);

        request = GetWordsToLearnRequest.builder()
                .newWordsCount(5)
                .repeatWordsDifficultCount(0)
                .repeatWordsRandomCount(0)
                .repeatWordsElderCount(2)
                .build();
        words = getWordsService.getWordsToLearns(request, 1L).getWords();
        assertEquals(words.size(), 7);

        request = GetWordsToLearnRequest.builder()
                .newWordsCount(5)
                .repeatWordsDifficultCount(2)
                .repeatWordsRandomCount(0)
                .repeatWordsElderCount(0)
                .build();
        words = getWordsService.getWordsToLearns(request, 1L).getWords();
        assertEquals(words.size(), 7);

        request = GetWordsToLearnRequest.builder()
                .newWordsCount(5)
                .repeatWordsDifficultCount(0)
                .repeatWordsRandomCount(2)
                .repeatWordsElderCount(0)
                .build();
        words = getWordsService.getWordsToLearns(request, 1L).getWords();
        assertEquals(words.size(), 7);

    }
}
