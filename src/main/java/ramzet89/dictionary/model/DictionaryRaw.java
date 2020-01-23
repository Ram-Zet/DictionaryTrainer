package ramzet89.dictionary.model;

import java.util.Objects;

public class DictionaryRaw {
    String word;
    String translate;

    public DictionaryRaw() {
    }

    public DictionaryRaw(String word, String translate) {
        this.word = word;
        this.translate = translate;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryRaw that = (DictionaryRaw) o;
        return Objects.equals(word, that.word) &&
                Objects.equals(translate, that.translate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(word, translate);
    }

    @Override
    public String toString() {
        return String.format("%-20s %s",getWord(), getTranslate());
    }
}
