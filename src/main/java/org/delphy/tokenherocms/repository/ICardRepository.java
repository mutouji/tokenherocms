package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author mutouji
 */
public interface ICardRepository extends MongoRepository<Card, String> {
}
