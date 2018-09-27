package com.epam.htsa.service;

import com.epam.htsa.entity.Item;

import javax.validation.constraints.NotNull;

/**
 * @author Oleksandr_Velchenko
 *
 * @param <T>
 *            Parent class for all entities
 */
public interface ItemService<T extends Item> {

    /**
     * Saving new object to storage or updating existing one
     * 
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public T save(@NotNull T object);

    /**
     * Removing object from storage
     * 
     * @param object
     *            Object to remove
     */
    public void remove(@NotNull T object);

    /**
     * Getting object by id from storage
     * 
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    public T getById(@NotNull Long id);

    /**
     * Getting all objects from storage
     * 
     * @return collection of objects
     */
    public Iterable<T> getAll();
}
