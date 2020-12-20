package ramzet89.dictionary.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ramzet89.dictionary.model.commons.DictionaryRecord;

import java.util.List;

@Getter
@Setter
@ToString
public class SaveLearnedRequest {
    private List<DictionaryRecord> learnedWords;
}
