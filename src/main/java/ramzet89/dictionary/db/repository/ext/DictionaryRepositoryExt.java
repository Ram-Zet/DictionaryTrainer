package ramzet89.dictionary.db.repository.ext;

import ramzet89.dictionary.db.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface DictionaryRepositoryExt {
    List<Long> getOldestRepeatWordsIds(Set<Long> scope, int count, UserEntity userEntity);
    List<Long> getDifficultRepeatWordsIds(Set<Long> scope, int count, UserEntity userEntity);
}
