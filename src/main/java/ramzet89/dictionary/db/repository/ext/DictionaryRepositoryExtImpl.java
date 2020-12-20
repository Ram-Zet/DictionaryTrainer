package ramzet89.dictionary.db.repository.ext;


import ramzet89.dictionary.db.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class DictionaryRepositoryExtImpl implements DictionaryRepositoryExt {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Long> getOldestRepeatWordsIds(Set<Long> scope, int count, UserEntity userEntity) {
        Query query = em.createQuery("SELECT d.id FROM DictionaryEntity d " +
                "WHERE d.user = :user AND d.id IN :scope " +
                "ORDER BY d.attemptDate");
        query.setParameter("user", userEntity);
        query.setParameter("scope", scope);
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<Long> getDifficultRepeatWordsIds(Set<Long> scope, int count, UserEntity userEntity) {
        Query query = em.createQuery("SELECT d.id FROM DictionaryEntity d " +
                "WHERE d.user = :user AND d.id IN :scope " +
                "ORDER BY d.difficultyCoefficient DESC")
                .setParameter("user", userEntity)
                .setParameter("scope", scope)
                .setMaxResults(count);
        return query.getResultList();
    }
}
