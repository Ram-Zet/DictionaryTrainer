package ramzet89.diictionary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ramzet89.diictionary.io.IOHelper;
import ramzet89.diictionary.model.Dictionary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {
    private final static String fileName = "dictionary.txt";

    private final ObjectMapper objectMapper;
    private final IOHelper ioHelper;

    @Autowired
    public StorageService(ObjectMapper objectMapper, IOHelper ioHelper) {
        this.objectMapper = objectMapper;
        this.ioHelper = ioHelper;
    }

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
                Files.copy(file, Paths.get(fileName + ".tmp"));
            }
            objectMapper.writeValue(new File(fileName), dictionary);
        } catch (IOException e) {
            ioHelper.print("Ошибка при записи файла");
        }
    }

    public void printDictionary(Dictionary dictionary) {
        ioHelper.print("=====NEW WORDS=====");
        dictionary.getNewWords().forEach(e -> ioHelper.print(e.toString()));
        ioHelper.print("=====LEARNED WORDS=====");
        dictionary.getLearned().forEach(e -> ioHelper.print(e.toString()));
    }
}
