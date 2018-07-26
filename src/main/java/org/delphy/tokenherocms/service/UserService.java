package org.delphy.tokenherocms.service;

import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.Activity;
import org.delphy.tokenherocms.entity.Forecast;
import org.delphy.tokenherocms.entity.User;
import org.delphy.tokenherocms.pojo.ForecastVo;
import org.delphy.tokenherocms.repository.IActivityRepository;
import org.delphy.tokenherocms.repository.IForecastRepository;
import org.delphy.tokenherocms.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mutouji
 */
@Service
public class UserService {
    private IUserRepository userRepository;
    private IForecastRepository forecastRepository;
    private IActivityRepository activityRepository;

    public UserService(@Autowired IUserRepository userRepository,
                       @Autowired IActivityRepository activityRepository,
                       @Autowired IForecastRepository forecastRepository) {
        this.userRepository = userRepository;
        this.forecastRepository = forecastRepository;
        this.activityRepository = activityRepository;
    }

    public RestResult<List<User>> getUsers(Integer page, Integer size, String name) {
        Pageable pageable = new PageRequest(page, size);
        List<User> users;
        if (name == null || name.isEmpty()) {
            users = this.userRepository.findAllByOrderByIdDesc(pageable);
        } else {
            users = this.userRepository.findByNameLikeOrderByIdDesc(name, pageable);
        }
        return new RestResult<>(0, Constant.SUCCESS, users);
    }

    public RestResult<Long> getUsersCount(String name) {
        Long count;
        if (name == null || name.isEmpty()) {
            count = this.userRepository.count();
        } else {
            count = this.userRepository.countByNameLike(name);
        }
        return new RestResult<>(0, Constant.SUCCESS, count);
    }

    public RestResult<List<ForecastVo>> getUserForecasts(String userId, Integer page, Integer size) {
        User user = userRepository.findOne(userId);
        Pageable pageable = new PageRequest(page, size);
        List<Forecast> forecasts = forecastRepository.findByUserIdOrderByIdDesc(userId, pageable);
        List<String> activityIds = new ArrayList<>(forecasts.size());
        for (Forecast forecast : forecasts) {
            activityIds.add(forecast.getActivityId());
        }
        List<Activity> activities = this.activityRepository.findByIdIn(activityIds);
        Map<String, Activity> activityMap = new HashMap<>(activities.size());
        for (Activity activity : activities) {
            activityMap.put(activity.getId(), activity);
        }
        List<ForecastVo> forecastVos = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            Activity activity = activityMap.get(forecast.getActivityId());
            ForecastVo forecastVo = new ForecastVo(forecast, activity, user);
            forecastVos.add(forecastVo);
        }
        return new RestResult<>(0, Constant.SUCCESS, forecastVos);
    }

    public RestResult<Long> getUserForecastsCount(String userId) {
        Long count = forecastRepository.countByUserId(userId);
        return new RestResult<>(0, Constant.SUCCESS, count);
    }
}
