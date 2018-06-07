package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author mutouji
 */
public interface IUserRepository extends MongoRepository<User, String> {
    /**
     * get all user sorted by create time in descent order
     * @param page return page size and offset
     * @return all user in that page
     */
    List<User> findAllByOrderByCreateDesc(Pageable page);
}
