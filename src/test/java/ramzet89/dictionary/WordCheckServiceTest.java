package ramzet89.dictionary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ramzet89.dictionary.model.DictionaryRaw;
import ramzet89.dictionary.model.WordCheckResult;
import ramzet89.dictionary.service.WordCheckService;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class WordCheckServiceTest {
    @Autowired
    private WordCheckService wordCheckService;

    @Test
    public void testOneWord() throws Exception {
        String userAnswer = "лошадь";
        DictionaryRaw dictionaryRaw = new DictionaryRaw("horse", "лошадь");
        WordCheckResult result = wordCheckService.checkWord(userAnswer, dictionaryRaw);
        assertEquals(dictionaryRaw.toString(), result, WordCheckResult.RIGHT);
    }

    @Test
    public void testWrongWord() throws Exception {
        String userAnswer = "лошадьЭ";
        DictionaryRaw dictionaryRaw = new DictionaryRaw("horse", "лошадь");
        WordCheckResult result = wordCheckService.checkWord(userAnswer, dictionaryRaw);
        assertEquals(dictionaryRaw.toString(), result, WordCheckResult.WRONG);
    }

    @Test
    public void testTwoWordsRightOrder() throws Exception {
        String userAnswer = "объем, масса";
        DictionaryRaw dictionaryRaw = new DictionaryRaw("bulk", "объем, масса");
        WordCheckResult result = wordCheckService.checkWord(userAnswer, dictionaryRaw);
        assertEquals(dictionaryRaw.toString(), result, WordCheckResult.RIGHT);
    }

    @Test
    public void testTwoWordsAnotherOrder() throws Exception {
        String userAnswer = "масса, объем";
        DictionaryRaw dictionaryRaw = new DictionaryRaw("bulk", "объем, масса");
        WordCheckResult result = wordCheckService.checkWord(userAnswer, dictionaryRaw);
        assertEquals(dictionaryRaw.toString(), result, WordCheckResult.RIGHT);
    }

    @Test
    public void testNotFullAnswer() throws Exception {
        String userAnswer = "объем";
        DictionaryRaw dictionaryRaw = new DictionaryRaw("bulk", "объем, масса");
        WordCheckResult result = wordCheckService.checkWord(userAnswer, dictionaryRaw);
        assertEquals(dictionaryRaw.toString(), result, WordCheckResult.NEED_LEARN);
    }
}
