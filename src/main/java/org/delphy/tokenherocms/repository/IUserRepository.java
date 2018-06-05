package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
//    Page<User> findAll(Pageable page);
}
