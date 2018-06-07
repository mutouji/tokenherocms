package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Withdraw;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IWithdrawRepository extends MongoRepository<Withdraw, String> {
}
