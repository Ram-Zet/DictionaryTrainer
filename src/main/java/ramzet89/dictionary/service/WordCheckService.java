package ramzet89.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ramzet89.dictionary.io.IOHelper;
import ramzet89.dictionary.model.WordCheckResult;
import ramzet89.dictionary.model.DictionaryRaw;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class WordCheckService {
    private final IOHelper ioHelper;

    @Autowired
    public WordCheckService(IOHelper ioHelper) {
        this.ioHelper = ioHelper;
    }

    public WordCheckResult checkWord(String userAnswer,
                                     DictionaryRaw rightRaw) {
//        Set<String> rightAnswers = answerToSetUpperCase(rightRaw.getTranslate());
//        Set<String> userAnswers = answerToSetUpperCase(userAnswer);
//        int rightCount = 0;
//        for (String answer : userAnswers) {
//            if (rightAnswers.contains(answer)) {
//                rightCount++;
//            }
//        }
//        if (rightCount == 0) {
//            return WordCheckResult.WRONG;
//        }
//        if (rightCount == rightAnswers.size()) {
//            return WordCheckResult.RIGHT;
//        }
        return WordCheckResult.NEED_LEARN;
    }

    private Set<String> answerToSetUpperCase(String answer) {
        Set<String> answersSet = new HashSet<>();
        Arrays.asList(answer.trim().split(",")).forEach(e -> {
            answersSet.add(e.trim().toUpperCase());
        });
        return answersSet;
    }
}
