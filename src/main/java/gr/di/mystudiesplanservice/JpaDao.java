package gr.di.mystudiesplanservice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class JpaDao<T extends Identifiable<PK>, PK> implements Dao<T, PK> {
    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public T create(T t) {
        if(t != null) {
            entityManager.persist(t);
        }

        return t;
    }

    public T read(PK id) {
        T t = entityManager.find(entityClass, id);
        return t;
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public void delete(T t) {
        if(t != null) {
            t = entityManager.merge(t);
            entityManager.remove(t);
        }
    }

    public void delete(PK pk) {
        T t = read(pk);
        if(t != null) {
            t = entityManager.merge(t);
            entityManager.remove(t);
        }
    }

    public List<T> getAll() {
        List<T> result = entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        return result == null ? new ArrayList<>() : result;
    }

    public long count() {
        return ((Number) entityManager.createQuery("select count(e) from " + entityClass.getSimpleName() + " e").getSingleResult()).longValue();
    }
}
