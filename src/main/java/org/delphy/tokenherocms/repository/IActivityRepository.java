package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.entity.Activity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author mutouji
 */
public interface IActivityRepository extends MongoRepository<Activity, String> {
    /**
     * get all Activity sorted by create time in descent order
     * @param delete 1 deleted
     * @param page page size and  offset
     * @param mode 0 微信小程序活动 1 h5活动
     * @return all activity in that page
     */
    List<Activity> findByDeleteAndModeOrderByIdDesc(Long delete, Long mode, Pageable page);

    /**
     * get all Activities sorted by create time in descent order in the status
     * @param status 未开始1，进行中2，锁定中3，清算中4，已结束5
     * @param delete 1 deleted
     * @param page page size and offset
     * @param mode 0 微信小程序活动 1 h5活动
     * @return all activities in the status
     */
    List<Activity> findByStatusAndDeleteAndModeOrderByIdDesc(Long status, Long delete, Long mode, Pageable page);

    /**
     * get count in the status
     * @param status 未开始1，进行中2，锁定中3，清算中4，已结束5
     * @param delete 1 deleted
     * @param mode 0 微信小程序活动 1 h5活动
     * @return count
     */
    Long countByStatusAndDeleteAndMode(Long status, Long delete, Long mode);

    /**
     * get all count base on mode
     * @param delete 0 not delete , 1 deleted
     * @param mode 0 微信小程序活动 1 h5活动
     * @return get all count base on mode
     */
    Long countByDeleteAndMode(Long delete, Long mode);

    /**
     *
     * @param ids ids
     * @return return
     */
    List<Activity> findByIdIn(List<String> ids);
    /**
     * used for update
     * @param entity entity
     * @param <S> Activity
     * @return return
     */
    @Override
    @Caching(evict = {
            @CacheEvict(value = "activity", key="#p0.id"),
            @CacheEvict(value = "todayActivities", allEntries = true)
    })

    <S extends Activity> S save(S entity);
}
