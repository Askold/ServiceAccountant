package ru.silonov.accountant.api;


import ru.silonov.accountant.model.AccountantEntity;

import java.util.List;
import java.util.Optional;

/**
 * Interface for entity CRUD's
 *
 * @author Silonov Kirill
 */
public interface IDataProvider {
    /**
     * Used to insert new raw into table
     * @param testEntity entity
     * @return inserted Entity
     */
    AccountantEntity insert(AccountantEntity testEntity);

    /**
     * Used to find ro by id and transfer it to entity
     * @param id raw identification (primary key)
     * @return Entity in Optional wrapper
     */
    Optional<AccountantEntity> getById(long id);

    /**
     * Used to select all raws from table
     * @return List consist of Entities
     */
    List<AccountantEntity> selectAll();

    /**
     * Used to update raw in table
     * @param testEntity updated Entity
     * @return result of inserting
     */
    boolean update(AccountantEntity testEntity);

    /**
     * Used to delete a raw from table
     * @param id raw identification (primary key)
     * @return result of inserting
     */
    boolean delete(long id);
}
