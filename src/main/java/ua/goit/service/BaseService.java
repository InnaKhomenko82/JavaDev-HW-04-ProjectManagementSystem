package ua.goit.service;

import ua.goit.models.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    T createEntity(Class<T> eClass, T t);
    Optional<T> readById(Class<T> eClass, ID id);
    T updateEntity(Class<T> eClass, T t);
    void deleteById(Class<T> eClass, ID id);
    List<T> findAll(Class<T> eClass);
}
