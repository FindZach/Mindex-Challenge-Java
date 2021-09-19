package com.mindex.challenge.service;

import java.util.Set;
/**
 * @author Zach <zach@findzach.com>
 * @since 9/18/2021
 */
public interface CrudService<T, ID> {

    /**
     * Finds all objects
     * @return Returns a Set of objects
     */
    Set<T> findAll();

    /**
     * Finds object with ID
     * @param id - The ID of the object
     * @return Returns the object
     */
    T findById(ID id);

    /**
     * Creates an object
     * @param object - The object to create
     * @return Returns the created object
     */
    T create(T object);

    /**
     * Deletes object
     * @param object - The object to delete
     */
    void delete(T object);

    /**
     * Updates Object with ID
     * @param id - ID of the Object to update
     * @param object - The object update
     * @return Returns the updated object
     */
    T updateWithId(ID id, T object);

    /**
     * Updates the object
     * @param object The object to update
     * @return Returns the updated object
     */
    T update(T object);

    /**
     * Deletes Object , finds with ID
     * @param id - The ID of the object
     * @return Returns true if deleted, false if no record exists
     */
    boolean deleteById(ID id);
}

