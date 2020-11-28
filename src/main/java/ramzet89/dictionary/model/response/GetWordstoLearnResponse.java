package ramzet89.dictionary.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class GetWordstoLearnResponse {
    private List<DictionaryRecord> dictionaryRecordList = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    public static class DictionaryRecord {
        private Long id;
        private String english;
        private String russian;
        private String russian2;
        private String russian3;
        private String transcription;
        private Integer attempts;
        private Integer failures;
    }
}
