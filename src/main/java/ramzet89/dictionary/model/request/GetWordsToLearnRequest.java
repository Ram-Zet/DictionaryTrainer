package ramzet89.dictionary.model.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetWordsToLearnRequest {
    private Integer newWordsCount;
    private Integer repeatWordsRandomCount;
    private Integer repeatWordsElderCount;
    private Integer repeatWordsDifficultCount;
}
