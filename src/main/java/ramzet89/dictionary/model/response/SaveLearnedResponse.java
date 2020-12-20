package ramzet89.dictionary.model.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveLearnedResponse {
    private Set<Long> savedWordsIds;
}
