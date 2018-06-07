package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Forecast;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author mutouji
 */
public interface IForecastRepository extends MongoRepository<Forecast, String> {
}
