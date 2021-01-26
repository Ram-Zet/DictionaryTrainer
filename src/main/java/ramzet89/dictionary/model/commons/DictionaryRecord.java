package ramzet89.dictionary.model.commons;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryRecord {
    private Long id;
    private String english;
    private String russian;
    private String russian2;
    private String russian3;
    private String transcription;
    private Integer attempts;
    private Integer failures;
}
