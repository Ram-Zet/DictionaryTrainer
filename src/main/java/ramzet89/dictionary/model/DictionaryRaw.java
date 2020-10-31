package ramzet89.dictionary.model;

import lombok.Data;

@Data
public class DictionaryRaw {
    private Long id;
    private String english;
    private String russian;
    private String russian2;
    private String russian3;
    private Integer attempts;
    private Integer failures;

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s attempts: %-4d failures: %-4d",
                english, russian, russian2, russian3, attempts, failures);
    }
}
