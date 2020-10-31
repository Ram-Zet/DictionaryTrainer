package ramzet89.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ramzet89.dictionary.io.IOHelper;

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

    }

    public void moveToDb() {

    }
}
