package com.mindex.challenge.service;

import java.util.Set;
/**
 * @author Zach <zach@findzach.com>
 * @since 9/18/2021
 */
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T create(T object);

    void delete(T object);

    /**
     *
     * @param id
     * @param object
     * @return - Returns
     */
    T updateWithId(ID id, T object);

    /**
     * Updates the object
     * @param object The object to update
     * @return - Returns the updated object
     */
    T update(T object);

    /**
     * Deletes Object , finds with ID
     * @param id - The ID of the object
     * @return - Returns true if deleted, false if no record exists
     */
    boolean deleteById(ID id);
}

