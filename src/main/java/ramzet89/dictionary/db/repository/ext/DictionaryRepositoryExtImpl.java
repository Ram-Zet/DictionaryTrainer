package ramzet89.dictionary.db.repository.ext;


import org.springframework.util.CollectionUtils;
import ramzet89.dictionary.db.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DictionaryRepositoryExtImpl implements DictionaryRepositoryExt {

    private final static String OLDEST_REPEAT_WORDS_QUERY = "SELECT d.id FROM DictionaryEntity d " +
            " WHERE d.user = :user AND d.id IN :scope " +
            " ORDER BY d.attemptDate";
    private final static String DIFFICULT_REPEAT_WORDS_QUERY = "SELECT d.id FROM DictionaryEntity d " +
            " WHERE d.user = :user AND d.id IN :scope " +
            " ORDER BY d.difficultyCoefficient DESC";

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Long> getOldestRepeatWordsIds(Set<Long> scope, int count, UserEntity userEntity) {
        return CollectionUtils.isEmpty(scope) ? Collections.emptyList() :
               createRepeatWordsQuery(OLDEST_REPEAT_WORDS_QUERY, userEntity, scope, count).getResultList();
    }

    @Override
    public List<Long> getDifficultRepeatWordsIds(Set<Long> scope, int count, UserEntity userEntity) {
        return CollectionUtils.isEmpty(scope) ? Collections.emptyList() :
                createRepeatWordsQuery(DIFFICULT_REPEAT_WORDS_QUERY, userEntity, scope, count).getResultList();
    }

    private Query createRepeatWordsQuery(String query, UserEntity user, Set<Long> scope, int maxResults) {
        return em.createQuery(query)
                .setParameter("user", user)
                .setParameter("scope", scope)
                .setMaxResults(maxResults);
    }
}
