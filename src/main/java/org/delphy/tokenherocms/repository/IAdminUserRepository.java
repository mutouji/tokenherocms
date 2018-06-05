package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Transactional
public interface IAdminUserRepository extends MongoRepository<AdminUser, String> {

}
