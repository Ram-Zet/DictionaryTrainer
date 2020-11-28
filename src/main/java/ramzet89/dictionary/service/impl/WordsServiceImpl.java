package ramzet89.dictionary.service.impl;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.db.entity.UserEntity;
import ramzet89.dictionary.db.repository.DictionaryRepository;
import ramzet89.dictionary.db.repository.UserRepository;
import ramzet89.dictionary.mapper.DictionaryMapper;
import ramzet89.dictionary.model.request.GetWordsToLearnRequest;
import ramzet89.dictionary.model.response.GetWordstoLearnResponse;
import ramzet89.dictionary.service.WordsService;
import ramzet89.dictionary.util.CustomCollectionRandomizer;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WordsServiceImpl implements WordsService {
    private final static Random rand = new Random();
    private final DictionaryRepository dictionaryRepository;
    private final UserRepository userRepository;
    private final DictionaryMapper dictionaryMapper;

    @Override
    public GetWordstoLearnResponse getWordsToLearns(@NotNull GetWordsToLearnRequest request,
                                                    @NotNull Long userId) {
        UserEntity user = getUserById(userId);
        Set<Long> ids = getWordsIds(request, user);
        List<DictionaryEntity> resultWords = dictionaryRepository.findAllById(ids);
        return GetWordstoLearnResponse.builder()
                .dictionaryRecordList(dictionaryMapper.toDictionaryRecordList(resultWords))
                .build();
    }

    private Set<Long> getWordsIds(GetWordsToLearnRequest request, UserEntity user) {
        Set<Long> resultIds = getNewWordsIds(request.getNewWordsCount(), user);
        Set<Long> repeatIds = getRepeatIds(request, user);
        resultIds.addAll(repeatIds);
        return resultIds;
    }

    private Set<Long> getNewWordsIds(int count, UserEntity user) {
        Set<Long> ids = Collections.unmodifiableSet(dictionaryRepository.getNewWordsIds(user));
        return CustomCollectionRandomizer.getRandomItemIds(ids, count);
    }

    private Set<Long> getRepeatIds(GetWordsToLearnRequest request, UserEntity user) {
        //all
        Set<Long> allRepeatWordsIds = dictionaryRepository.getAllRepeatWordsIds(user);
        if (allRepeatWordsIds.isEmpty()) {
            return allRepeatWordsIds;
        }
        //random
        Set<Long> resultIds = CustomCollectionRandomizer.getRandomItemIds(allRepeatWordsIds, request.getRepeatWordsRandomCount());
        allRepeatWordsIds.removeAll(resultIds);
        if (allRepeatWordsIds.isEmpty()) {
            return resultIds;
        }
        //oldest
        List<Long> oldestRepeatWordsIds = dictionaryRepository.getOldestRepeatWordsIds(resultIds, request.getRepeatWordsElderCount(), user);
        resultIds.addAll(oldestRepeatWordsIds);
        allRepeatWordsIds.removeAll(oldestRepeatWordsIds);
        if (allRepeatWordsIds.isEmpty()) {
            return resultIds;
        }
        //difficult
        List<Long> diffcultRepeatWorsIds = dictionaryRepository.getDifficultRepeatWordsIds(allRepeatWordsIds, request.getRepeatWordsDifficultCount(), user);
        resultIds.addAll(diffcultRepeatWorsIds);

        return resultIds;
    }

    private UserEntity getUserById(@NotNull Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id " + userId));
    }


}
