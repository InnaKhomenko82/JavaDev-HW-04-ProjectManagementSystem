package ua.goit.service;

import ua.goit.models.BaseEntity;
import ua.goit.repository.RepositoryFactory;

import java.util.List;
import java.util.Optional;

public abstract class ServiceCrud<E extends BaseEntity <ID>, ID> implements BaseService<E, ID> {

    public E createEntity(Class<E> eClass, E e){
        return RepositoryFactory.of(eClass).save(e);
    }

    public Optional<E> readById(Class<E> eClass, ID id){
        return RepositoryFactory.of(eClass).findById(id);
    }

    public E updateEntity(Class<E> eClass, E e){
        return RepositoryFactory.of(eClass).save(e);
    }

    public void deleteById(Class<E> eClass, ID id){
        RepositoryFactory.of(eClass).deleteById(id);
    }

    public List<E> findAll(Class<E> eClass){
        return RepositoryFactory.of(eClass).findAll();
    }
}
