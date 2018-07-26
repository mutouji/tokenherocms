package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Forecast;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author mutouji
 */
public interface IForecastRepository extends MongoRepository<Forecast, String> {
    /**
     * get one use's forecasts
     * @param userId one user
     * @param page pageIndex and pageSize
     * @return one use's forecasts
     */
    List<Forecast> findByUserIdOrderByIdDesc(String userId, Pageable page);

    /**
     * one use's forecasts's count
     * @param userId one user
     * @return one use's forecasts's count
     */
    Long countByUserId(String userId);
}
