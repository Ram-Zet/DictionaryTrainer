package ramzet89.dictionary.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ramzet89.dictionary.model.commons.DictionaryRecord;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class AddWordsResponse {
    private List<DictionaryRecord> addedRecords;
    private List<DictionaryRecord> duplicatedRecords;
    private List<DictionaryRecord> wrongRecords;
}
