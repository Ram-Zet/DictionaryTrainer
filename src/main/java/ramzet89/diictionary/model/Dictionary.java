package ramzet89.diictionary.model;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    List<DictionaryRaw> learned = new ArrayList<>();
    List<DictionaryRaw> newWords = new ArrayList<>();

    public List<DictionaryRaw> getLearned() {
        return learned;
    }

    public void setLearned(List<DictionaryRaw> learned) {
        this.learned = learned;
    }

    public List<DictionaryRaw> getNewWords() {
        return newWords;
    }

    public void setNewWords(List<DictionaryRaw> newWords) {
        this.newWords = newWords;
    }
}
