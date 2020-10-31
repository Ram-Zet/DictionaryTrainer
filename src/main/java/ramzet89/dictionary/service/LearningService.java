package ramzet89.dictionary.service;

import org.springframework.stereotype.Service;
import ramzet89.dictionary.io.IOHelper;

import java.util.Random;

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
}
