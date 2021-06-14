package ramzet89.dictionary.model.response;

import lombok.*;
import ramzet89.dictionary.model.commons.DictionaryRecord;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveLearnedResponse {
    private List<DictionaryRecord> saved;
}
