package ramzet89.dictionary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ramzet89.dictionary.model.request.GetWordsToLearnRequest;
import ramzet89.dictionary.model.request.SaveLearnedRequest;
import ramzet89.dictionary.model.response.GetWordstoLearnResponse;
import ramzet89.dictionary.model.response.SaveLearnedResponse;
import ramzet89.dictionary.service.SaveLearnedService;
import ramzet89.dictionary.service.impl.GetWordsServiceImpl;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/learn")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('user:learn', 'user:add')")
public class LearnController {

    private final GetWordsServiceImpl wordsService;
    private final SaveLearnedService saveLearnedService;

    @GetMapping("/words")
    public ResponseEntity<GetWordstoLearnResponse> getWordsToLearn(
            @RequestParam(required = false, defaultValue = "${learn.getWordsToLearn.newWordsCount}")
                    Integer newWords,
            @RequestParam(required = false, defaultValue = "${learn.getWordsToLearn.repeatWordsRandomCount}")
                    Integer repeatWordsRandom,
            @RequestParam(required = false, defaultValue = "${learn.getWordsToLearn.repeatWordsDifficultCount}")
                    Integer repeatWordsDifficult,
            @RequestParam(required = false, defaultValue = "${learn.getWordsToLearn.repeatWordsElderCount}")
                    Integer repeatWordsElder) {
        GetWordsToLearnRequest learnRequest = GetWordsToLearnRequest.builder()
                .newWordsCount(newWords)
                .repeatWordsRandomCount(repeatWordsRandom)
                .repeatWordsElderCount(repeatWordsElder)
                .repeatWordsDifficultCount(repeatWordsDifficult)
                .build();

        GetWordstoLearnResponse response = wordsService.getWordsToLearns(learnRequest, 1L);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/savelearned")
    public ResponseEntity<SaveLearnedResponse> saveLearned(@RequestBody SaveLearnedRequest saveLearnedRequest) {
        Set<Long> savedWordsIds = saveLearnedService.saveLearningResult(saveLearnedRequest, 1L);
        SaveLearnedResponse saveLearnedResponse = new SaveLearnedResponse(savedWordsIds);
        return new ResponseEntity<>(saveLearnedResponse, HttpStatus.OK);
    }
}
