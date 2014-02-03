package com.zhadan.dao.interfaces;


import com.zhadan.exceptions.DAOException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/6/13
 * Time: 10:00 AM
 */

/**
 * This interface represents a contract for a DAO for the {@link T} model.
 * Note that all methods which returns the {@link T} from the DB, will not
 */
public interface BasicDao<T> {

    /**
     * Returns the entity from the database matching the given ID, otherwise null.
     *
     * @param id The ID of the user to be returned.
     * @return The entity from the database matching the given ID, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    public T find(Integer id) throws DAOException;

    /**
     * Returns a list of all entities from the database. The list is never null and
     * is empty when the database does not contain any entity.
     *
     * @return A list of all entities from the database ordered by user ID.
     * @throws DAOException If something fails at database level.
     */
    public List<T> list() throws DAOException;

    /**
     * Returns a list of all entities from the database with offset and limit. The list is never null and
     * is empty when the database does not contain any entity.
     *
     * @return A list of all entities from the database ordered by user ID.
     * @throws DAOException If something fails at database level.
     */
    public List<T> list(int offset, int limit) throws DAOException;

    /**
     * Create the given entity in the database. The ID must be null, otherwise it will throw
     * IllegalArgumentException. After creating, the DAO will set the obtained ID in the given entity.
     *
     * @param entity The entity to be created in the database.
     * @throws IllegalArgumentException If the ID is not null.
     * @throws DAOException             If something fails at database level.
     */
    public void insert(T entity) throws IllegalArgumentException, DAOException;

    /**
     * Update the given entity in the database. The ID must not be null, otherwise it will throw
     * IllegalArgumentException.
     *
     * @param entity The entity to be updated in the database.
     * @throws IllegalArgumentException If the ID is null.
     * @throws DAOException             If something fails at database level.
     */
    public void update(T entity) throws IllegalArgumentException, DAOException;

    /**
     * Delete the given entity from the database. After deleting, the DAO will set the ID of the given
     * entity to null.
     *
     * @param entity The entity to be deleted from the database.
     * @throws DAOException If something fails at database level.
     */
    public void delete(T entity) throws DAOException;

    public int getSize();
}
