package ramzet89.dictionary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ramzet89.dictionary.model.request.GetWordsToLearnRequest;
import ramzet89.dictionary.model.response.GetWordstoLearnResponse;
import ramzet89.dictionary.service.impl.WordsServiceImpl;

@RestController
@RequestMapping("/learn")
@RequiredArgsConstructor
public class LearnController {

    private final WordsServiceImpl wordsService;

    @GetMapping("/words")
    public ResponseEntity<GetWordstoLearnResponse> getWordsToLearn(
            @RequestParam(required = false, defaultValue="${learn.getWordsToLearn.newWordsCount}")
                    Integer newWords,
            @RequestParam(required = false, defaultValue="${learn.getWordsToLearn.repeatWordsRandomCount}")
                    Integer repeatWordsRandom,
            @RequestParam(required = false, defaultValue="${learn.getWordsToLearn.repeatWordsDifficultCount}")
                    Integer repeatWordsDifficult,
            @RequestParam(required = false, defaultValue="${learn.getWordsToLearn.repeatWordsElderCount}")
                    Integer repeatWordsElder) {
        GetWordsToLearnRequest learnRequest = GetWordsToLearnRequest.builder()
                .newWordsCount(newWords)
                .repeatWordsRandomCount(repeatWordsRandom)
                .repeatWordsElderCount(repeatWordsElder)
                .repeatWordsDifficultCount(repeatWordsDifficult)
                .build();

        //TODO - security
        GetWordstoLearnResponse response = wordsService.getWordsToLearns(learnRequest, 1L);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
