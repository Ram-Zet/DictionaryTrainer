package ramzet89.dictionary.service;

import org.springframework.stereotype.Service;
import ramzet89.dictionary.io.IOHelper;
import ramzet89.dictionary.model.Dictionary;
import ramzet89.dictionary.model.DictionaryRaw;
import ramzet89.dictionary.model.WordCheckResult;

import java.util.*;

@Service
public class LearningService {
    private final static Random random = new Random();

    private final static Integer NEW_WORDS_COUNT = 10;
    private final static Integer REPEAT_WORDS_COUNT = 10;
    private final StorageService storageService;
    private final IOHelper ioHelper;
    private final WordCheckService wordCheckService;

    public LearningService(StorageService storageService, IOHelper ioHelper, WordCheckService wordCheckService) {
        this.storageService = storageService;
        this.ioHelper = ioHelper;
        this.wordCheckService = wordCheckService;
    }

    public void prepareAndLearn() {
        Dictionary dictionary = storageService.getDictionary();
        Map<DictionaryRaw, Integer> newWords = getNewWords(dictionary);
        Map<DictionaryRaw, Integer> repeatWords = getRepeatWords(dictionary);
        if (newWords.isEmpty() && repeatWords.isEmpty()) {
            ioHelper.print("Словарь пустой");
        }

        while (newWords.size() > 0 || repeatWords.size() > 0) {
            ioHelper.print("=================================");
            DictionaryRaw dictionaryRaw;
            Map<DictionaryRaw, Integer> rightMap;
            if (newWords.size() > 0
                    && random.nextInt(newWords.size() + repeatWords.size()) <= newWords.size()) {
                dictionaryRaw = new ArrayList<>(newWords.keySet()).get(random.nextInt(newWords.size()));
                rightMap = newWords;
            } else if (repeatWords.size() > 0) {
                dictionaryRaw = new ArrayList<>(repeatWords.keySet()).get(random.nextInt(repeatWords.size()));
                rightMap = repeatWords;
            } else {
                break;
            }
            ioHelper.print("word: " + dictionaryRaw.getWord());
            String userAnswer = ioHelper.readWord();
            WordCheckResult wordCheckResult = wordCheckService.checkWord(userAnswer, dictionaryRaw);
            switch (wordCheckResult) {
                case RIGHT:
                    Integer count = rightMap.get(dictionaryRaw);
                    count++;
                    if (count == 2) {
                        rightMap.remove(dictionaryRaw);
                        dictionary.getNewWords().remove(dictionaryRaw);
                        dictionary.getLearned().add(dictionaryRaw);
                    } else {
                        rightMap.put(dictionaryRaw, count);
                    }
                    break;
                case WRONG:
                    rightMap.put(dictionaryRaw, 0);
                    ioHelper.print("Неверно. Правильный ответ: '" + dictionaryRaw.getTranslate() + "'");
                    break;
                case NEED_LEARN:
                    ioHelper.print("Почти. Правильный ответ: '" + dictionaryRaw.getTranslate() + "'");
            }
        }

        ioHelper.print("Успещно выучено");
        storageService.saveDictionary(dictionary);
    }

    private Map<DictionaryRaw, Integer> getNewWords(Dictionary dictionary) {
        List<DictionaryRaw> raws = dictionary.getNewWords();
        Map<DictionaryRaw, Integer> result = new HashMap<>();
        int count = raws.size() > NEW_WORDS_COUNT ? NEW_WORDS_COUNT : raws.size();
        while (count > 0) {
            DictionaryRaw raw = raws.remove(random.nextInt(raws.size()));
            result.put(raw, 0);
            count--;
        }
        return result;
    }

    private Map<DictionaryRaw, Integer> getRepeatWords(Dictionary dictionary) {
        Random random = new Random();
        List<DictionaryRaw> raws = dictionary.getLearned();
        Map<DictionaryRaw, Integer> result = new HashMap<>();
        int count = raws.size() > REPEAT_WORDS_COUNT ? REPEAT_WORDS_COUNT : raws.size();
        while (count > 0) {
            DictionaryRaw raw = raws.remove(random.nextInt(raws.size()));
            result.put(raw, 0);
            count--;
        }
        return result;
    }
}
