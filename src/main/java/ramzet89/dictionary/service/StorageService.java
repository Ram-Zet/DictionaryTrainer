package ramzet89.dictionary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.db.repository.DictionaryRepository;
import ramzet89.dictionary.io.IOHelper;
import ramzet89.dictionary.model.Dictionary;
import ramzet89.dictionary.model.DictionaryRaw;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final static String fileName = "dictionary.json";

    private final ObjectMapper objectMapper;
    private final IOHelper ioHelper;
    private final DictionaryRepository dictionaryRepository;


    public Dictionary getDictionary() {
        try {
            return objectMapper.readValue(new File(fileName), Dictionary.class);
        } catch (IOException e) {
            ioHelper.print("Ошибка чтения из файла, возвращаю пустой словарь");
            return new Dictionary();
        }
    }

    public void saveDictionary(Dictionary dictionary) {
        try {
            Path file = Paths.get(fileName);
            if (Files.exists(file)) {
                Files.copy(file, Paths.get(fileName + ".tmp"), REPLACE_EXISTING);
            }
            Files.delete(file);
            objectMapper.writeValue(new File(fileName), dictionary);
        } catch (IOException e) {
            ioHelper.print("Ошибка при записи файла");
        }
    }

    @Transactional
    public void moveToDb(Dictionary dictionary) {
        Set<DictionaryEntity> newWordEntities = dictionary.getNewWords().stream()
                .map(this::convertRawToEntity)
                .collect(Collectors.toSet());
        Set<DictionaryEntity> learnedWordEntites = dictionary.getLearned().stream()
                .map(this::convertRawToEntity)
                .collect(Collectors.toSet());
        newWordEntities.addAll(learnedWordEntites);
        dictionaryRepository.saveAll(newWordEntities);
        ioHelper.print("Moved to db");
    }

    public void printDictionary(Dictionary dictionary) {
        ioHelper.print("=====NEW WORDS=====");
        dictionary.getNewWords().forEach(e -> ioHelper.print(e.toString()));
        ioHelper.print("=====LEARNED WORDS=====");
        dictionary.getLearned().forEach(e -> ioHelper.print(e.toString()));
    }

    private DictionaryEntity convertRawToEntity(DictionaryRaw dictionaryRaw) {
        DictionaryEntity dictionaryEntity = new DictionaryEntity();
        dictionaryEntity.setEnglish(dictionaryRaw.getWord());
        String[] translations = dictionaryRaw.getTranslate().split(",");
        dictionaryEntity.setRussian(translations[0]);
        if (translations.length > 1) {
            dictionaryEntity.setRussian2(translations[1]);
        }
        if (translations.length > 2) {
            dictionaryEntity.setRussian3(translations[2]);
        }
        return dictionaryEntity;
    }
}
