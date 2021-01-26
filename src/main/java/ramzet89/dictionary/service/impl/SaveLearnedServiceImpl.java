package ramzet89.dictionary.service.impl;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.db.entity.UserEntity;
import ramzet89.dictionary.db.repository.DictionaryRepository;
import ramzet89.dictionary.db.repository.UserRepository;
import ramzet89.dictionary.model.commons.DictionaryRecord;
import ramzet89.dictionary.model.request.SaveLearnedRequest;
import ramzet89.dictionary.service.SaveLearnedService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SaveLearnedServiceImpl implements SaveLearnedService {
    private final UserRepository userRepository;
    private final DictionaryRepository dictionaryRepository;

    @Override
    public Set<Long> saveLearningResult(SaveLearnedRequest saveLearnedRequest, Long userId) {
        UserEntity user = getUserById(userId);
        List<DictionaryRecord> learnedWords = saveLearnedRequest.getLearnedWords();
        List<DictionaryEntity> learnedWordsEntities =
                dictionaryRepository.findAllByIdAndUser(
                        learnedWords.stream().map(DictionaryRecord::getId).collect(Collectors.toSet()), user);
        mergeWords(learnedWordsEntities, learnedWords);
        dictionaryRepository.saveAll(learnedWordsEntities);
        return learnedWordsEntities.stream().map(DictionaryEntity::getId).collect(Collectors.toSet());
    }

    private void mergeWords(List<DictionaryEntity> learnedWordsEntities, List<DictionaryRecord> learnedWords) {
        Map<Long, DictionaryRecord> learnedWordsById =
                learnedWords.stream().collect(Collectors.toMap(DictionaryRecord::getId, Function.identity()));
        learnedWordsEntities.forEach(learnedWordsEntity -> {
            DictionaryRecord dictionaryRecord = learnedWordsById.get(learnedWordsEntity.getId());
            learnedWordsEntity.setAttemptDate(LocalDateTime.now());
            learnedWordsEntity.setAttempts(learnedWordsEntity.getAttempts() + dictionaryRecord.getAttempts());
            learnedWordsEntity.setFailures(learnedWordsEntity.getFailures() + dictionaryRecord.getFailures());
            learnedWordsEntity.setDifficultyCoefficient(((double) learnedWordsEntity.getFailures()) / learnedWordsEntity.getAttempts());
        });
    }


    private UserEntity getUserById(@NotNull Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id " + userId));
    }
}
