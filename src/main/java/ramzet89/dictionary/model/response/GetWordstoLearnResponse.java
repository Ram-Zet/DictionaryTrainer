package ramzet89.dictionary.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ramzet89.dictionary.model.commons.DictionaryRecord;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class GetWordstoLearnResponse {
    private List<DictionaryRecord> words = new ArrayList<>();
}
