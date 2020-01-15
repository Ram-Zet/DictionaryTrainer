package ramzet89.diictionary.io;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import ramzet89.diictionary.model.DictionaryRaw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConsoleHelper implements IOHelper {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readWord() {
        try {
            System.out.println("translate: ");
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public DictionaryRaw readDictionaryPair() {
        try {
            System.out.println("word: ");
            String word = reader.readLine();
            if (Strings.isEmpty(word)) {
                return null;
            }
            System.out.println("translate: ");
            String translate = reader.readLine();
            DictionaryRaw dictionaryRaw = new DictionaryRaw();
            dictionaryRaw.setWord(word);
            dictionaryRaw.setTranslate(translate);
            return dictionaryRaw;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
