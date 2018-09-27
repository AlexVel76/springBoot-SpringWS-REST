package com.epam.htsa.dao;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface AbstractDao<T> {
    /**
     * Saving new object to storage or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    public T save(@NotNull T object);

    /**
     * Removing object from storage
     *
     * @param object Object to remove
     */
    public void remove(@NotNull T object);

    /**
     * Getting object by id from storage
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    public T getById(@NotNull Long id);

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    public @NotNull List<T> getAll();
}
