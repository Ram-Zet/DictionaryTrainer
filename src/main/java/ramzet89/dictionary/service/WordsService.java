package ramzet89.dictionary.service;

import org.jetbrains.annotations.NotNull;
import ramzet89.dictionary.model.request.GetWordsToLearnRequest;
import ramzet89.dictionary.model.response.GetWordstoLearnResponse;

public interface WordsService {
    GetWordstoLearnResponse getWordsToLearns(@NotNull GetWordsToLearnRequest request,
                                             @NotNull Long userId);
}
