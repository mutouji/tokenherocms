package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author mutouji
 */
public interface IAdminUserRepository extends MongoRepository<AdminUser, String> {
    /**
     *
     * get one user who's account equals with account param
     *
     * @param account account name
     * @return one AdminUser who's account == account
     */
    AdminUser findOneByAccount(String account);
}
