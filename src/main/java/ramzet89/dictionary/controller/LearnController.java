package ramzet89.dictionary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ramzet89.dictionary.config.GetWordsToLearnConfig;
import ramzet89.dictionary.model.request.GetWordsToLearnRequest;

@RestController
@RequestMapping("/learn")
@RequiredArgsConstructor
public class LearnController {

    private final GetWordsToLearnConfig getWordsToLearnConfig;

    @GetMapping("/getwordstolearn")
    public ResponseEntity<GetWordsToLearnRequest> getWordsToLearn(@RequestBody GetWordsToLearnRequest getWordsToLearnRequest) {
        getWordsToLearnRequest.setNewWords(getWordsToLearnConfig.getNewWords());
        getWordsToLearnRequest.setDifficultToLearnWords(getWordsToLearnConfig.getDifficultToLearnWords());
        getWordsToLearnRequest.setRepeatWords(getWordsToLearnConfig.getRepeatWords());
        return new ResponseEntity<>(getWordsToLearnRequest, HttpStatus.OK);
    }
}
