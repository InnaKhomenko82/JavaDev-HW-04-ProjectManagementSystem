package ua.goit.repository;

import ua.goit.models.BaseEntity;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, E extends BaseEntity<ID>> {

    List<E> findAll();

    Optional<E> findById (ID id);

    void deleteById (ID id);

    List<E> saveAll(Iterable <E> itrb);

    E save(E e);

    void close();
}
