package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author mutouji
 */
public interface IActivityRepository extends MongoRepository<Activity, String> {
}
