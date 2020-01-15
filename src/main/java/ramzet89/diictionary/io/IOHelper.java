package ramzet89.diictionary.io;


import ramzet89.diictionary.model.DictionaryRaw;

public interface IOHelper {
    String readWord();
    DictionaryRaw readDictionaryPair();
    String readLine();
    default void print(String string) {
        System.out.println(string);
    }
}
