package ramzet89.dictionary.io;

import org.springframework.stereotype.Service;
import ramzet89.dictionary.model.DictionaryRaw;

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
        //TODO
        return new DictionaryRaw();
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
