package ramzet89.dictionary.model.request;


import lombok.Data;

@Data
public class GetWordsToLearnRequest {
    private Integer newWords;
    private Integer repeatWords;
    private Integer difficultToLearnWords;
}
