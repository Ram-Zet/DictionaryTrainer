package ramzet89.dictionary.io;


import ramzet89.dictionary.model.DictionaryRaw;

public interface IOHelper {
    String readWord();
    DictionaryRaw readDictionaryPair();
    String readLine();
    default void print(String string) {
        System.out.println(string);
    }
}
