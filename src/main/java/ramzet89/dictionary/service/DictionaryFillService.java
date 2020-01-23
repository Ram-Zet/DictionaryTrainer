package ramzet89.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ramzet89.dictionary.io.IOHelper;
import ramzet89.dictionary.model.Dictionary;
import ramzet89.dictionary.model.DictionaryRaw;

import java.util.Objects;

@Service
public class DictionaryFillService {

    private final IOHelper ioHelper;
    private final StorageService storageService;

    @Autowired
    public DictionaryFillService(IOHelper ioHelper,
                                 StorageService storageService) {
        this.ioHelper = ioHelper;
        this.storageService = storageService;
    }

    public void fillDictionary() {
        Dictionary dictionary = storageService.getDictionary();
        while (true) {
            DictionaryRaw raw = ioHelper.readDictionaryPair();
            if (Objects.isNull(raw)) {
                break;
            }
            dictionary.getNewWords().add(raw);
        }
        storageService.saveDictionary(dictionary);
    }
}
