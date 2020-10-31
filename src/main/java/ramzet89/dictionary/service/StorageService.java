package ramzet89.dictionary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ramzet89.dictionary.db.repository.DictionaryRepository;
import ramzet89.dictionary.io.IOHelper;
import ramzet89.dictionary.mapper.DictionaryMapper;
import ramzet89.dictionary.model.Dictionary;
import ramzet89.dictionary.model.DictionaryRaw;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final static String fileName = "dictionary.json";

    private final IOHelper ioHelper;
    private final DictionaryRepository dictionaryRepository;
    private final DictionaryMapper dictionaryMapper;


    @Transactional
    public Dictionary getDictionary() {
        List<DictionaryRaw> dictionaryRaws = dictionaryRepository.findAll().stream()
                .map(dictionaryMapper::mapToRaw)
                .collect(Collectors.toList());
        Dictionary dictionary = new Dictionary();
        dictionary.setDictionaryRaws(dictionaryRaws);
        return dictionary;
    }

    public void saveDictionary(Dictionary dictionary) {
    }

    public void printDictionary(Dictionary dictionary) {
        dictionary.getDictionaryRaws().forEach(this::printDictionaryRaw);
    }

    private void printDictionaryRaw(DictionaryRaw dictionaryRaw) {
        ioHelper.print(String.format("%-40s %-30s %-20s %-20s %d %d",
                dictionaryRaw.getEnglish(),
                dictionaryRaw.getRussian(),
                dictionaryRaw.getRussian2(),
                dictionaryRaw.getRussian3(),
                dictionaryRaw.getAttempts(),
                dictionaryRaw.getFailures()));
    }

}
