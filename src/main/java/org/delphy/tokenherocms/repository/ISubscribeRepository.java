package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Subscribe;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author mutouji
 */
public interface ISubscribeRepository extends MongoRepository<Subscribe, String> {
}
