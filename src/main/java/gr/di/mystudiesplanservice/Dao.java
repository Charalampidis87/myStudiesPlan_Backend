package gr.di.mystudiesplanservice;

import java.util.List;

public interface Dao<T extends Identifiable<PK>, PK> {
    T create(T t);

    T read(PK pk);

    T update(T t);

    void delete(T t);

    void delete(PK pk);

    List<T> getAll();
}
