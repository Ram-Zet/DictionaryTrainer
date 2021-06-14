package ramzet89.dictionary.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ramzet89.dictionary.model.commons.DictionaryRecord;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class AddWordsRequest {
    private List<DictionaryRecord> dictionaryRecords;
}
