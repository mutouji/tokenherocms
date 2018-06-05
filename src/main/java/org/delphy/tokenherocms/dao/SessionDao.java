package org.delphy.tokenherocms.dao;

import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.Constant;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SessionDao {
    @Autowired
    RedissonClient redissonClient;

    public void put(String sessionId, Object value, long timeToLive, TimeUnit timeUnit) {
        log.info("---put {0}", sessionId);
        redissonClient.getBucket(Constant.SESSION_PREFIX + sessionId).set(value, timeToLive, timeUnit);
    }


//    @Cacheable(cacheNames = Constant.SESSION_PREFIX + "#sessionId", condition = "rBucket.isExists()")
    public RBucket getBucket(String sessionId) {
        log.info("---get {0}", sessionId);
        RBucket rBucket = redissonClient.getBucket(Constant.SESSION_PREFIX + sessionId);
        return rBucket;
    }
}
