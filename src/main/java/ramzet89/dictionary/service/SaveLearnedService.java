package ramzet89.dictionary.service;

import ramzet89.dictionary.model.request.SaveLearnedRequest;

import java.util.Set;

public interface SaveLearnedService {
    Set<Long> saveLearningResult(SaveLearnedRequest saveLearnedRequest, Long userId);
}
