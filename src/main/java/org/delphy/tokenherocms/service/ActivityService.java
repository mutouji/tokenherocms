package org.delphy.tokenherocms.service;

import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.EnumError;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.Activity;
import org.delphy.tokenherocms.pojo.*;
import org.delphy.tokenherocms.repository.IActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author mutouji
 */
@Slf4j
@Service
public class ActivityService {
    private String oracleUrl;
    private RestTemplate restTemplate;
    private IActivityRepository activityRepository;
    private OracleService oracleService;

    public ActivityService(@Autowired IActivityRepository activityRepository,
                           @Autowired RestTemplate restTemplate,
                           @Autowired OracleService oracleService,
                           @Value("${oracle.url}") String oracleUrl) {
        this.activityRepository = activityRepository;
        this.restTemplate = restTemplate;
        this.oracleUrl = oracleUrl;
        this.oracleService = oracleService;
    }

    public RestResult<List<Activity>> getActivities(Integer status, Integer page, Integer size, Long mode) {
        long state = 0;
        if (status != null) {
            state = status;
        }
        List<Activity> activities;
        Pageable pageable = new PageRequest(page, size);
        if (state > 0) {
            activities = this.activityRepository.findByStatusAndDeleteAndModeOrderByIdDesc(state, 0L, mode, pageable);
        } else {
            activities = this.activityRepository.findByDeleteAndModeOrderByIdDesc(0L, mode, pageable);
        }
        return new RestResult<>(0, Constant.SUCCESS, activities);
    }

    public RestResult<Activity> findOne(String id) {
        Activity activity = this.activityRepository.findOne(id);
        return new RestResult<>(0, Constant.SUCCESS, activity);
    }

    public RestResult<String> updateOne(String id, ActivityVo vo) {
        Activity activity = this.activityRepository.findOne(id);
        EnumError enumError = vo.updateActivity(activity);
        if (ActivityVo.canUpdate(enumError)) {
            if (ActivityVo.needReCreateOracle(enumError)) {
                return tryCreateOracle(activity, false);
            } else {
                activityRepository.save(activity);
            }
        }
        return new RestResult<>(0, Constant.SUCCESS);
    }

    public RestResult<String> updateOneResult(String id, Double result) {
        Activity activity = this.activityRepository.findOne(id);
        activity.setResult(result);
        activityRepository.save(activity);
        return new RestResult<>(0, Constant.SUCCESS);
    }

    public RestResult<Double> getActivityOracleResult(String id) {
        Activity activityDB = this.activityRepository.findOne(id);
        OracleVo<OracleResultVo> resultVoOracleVo = this.oracleService.getResult(activityDB.getOracleId());
        if (resultVoOracleVo.getCode() == 0) {
            OracleResultVo oracleResultVo = resultVoOracleVo.getData();
            List<String> resultValues = oracleResultVo.getResultValues();
            if (resultValues.size() != 1) {
                log.error("resultValues size = 0, activity id = ", id);
                return new RestResult<>(1222, "resultValues size != 1," + resultValues );
            }
            Double result = Double.parseDouble(resultValues.get(0));
            return new RestResult<>(0, "success", result);
        } else {
            log.error("get oracle result error:" + resultVoOracleVo.getData());
            return new RestResult<>(1223, "get oracle error:" + resultVoOracleVo.getData() );
        }
    }

    public RestResult<String> deleteOne(String id) {
        Activity activity = this.activityRepository.findOne(id);
        if (activity == null) {
            return new RestResult<>(EnumError.ACTIVITY_NOT_EXIST.getCode(), EnumError.ACTIVITY_NOT_EXIST.getMsg());
        }
        activity.setDelete(1L);
        this.activityRepository.save(activity);
        return new RestResult<>(0, Constant.SUCCESS);
    }

    public RestResult<Long> getActivitiesCount(Integer status, Long mode) {
        long count;
        long state = 0L;
        if (status != null) {
            state = status;
        }
        if (state > 0) {
            count = this.activityRepository.countByStatusAndDeleteAndMode(state, 0L, mode);
        } else {
            count = this.activityRepository.countByDeleteAndMode(0L, mode);
        }
        return new RestResult<>(0, Constant.SUCCESS, count);
    }

    public RestResult<String> createActivity(ActivityVo vo, Long mode) {
        Activity activity = new Activity();
        activity.setMode(mode);
        EnumError enumError = vo.initActivity(activity);
        if (enumError.getCode() == 0) {
            return tryCreateOracle(activity, true);
        }
        return new RestResult<>(enumError.getCode(), enumError.getMsg(), activity.getId());
    }

    private RestResult<String> tryCreateOracle(Activity activity, boolean isNew) {
        OracleResponseVo<ActivityCreateOracleResultVo> result = createOracle(activity);
        if (result == null) {
            return new RestResult<>(EnumError.ORACLE_CODE.getCode(), "oracle none", activity.getId());
        }
        if (result.getCode() == 0) {
            String oracleId = result.getData().getOracleId().toString();
            activity.setOracleId(Long.parseLong(oracleId));
            if (isNew) {
                activityRepository.insert(activity);
            } else {
                activityRepository.save(activity);
            }
            return new RestResult<>(EnumError.SUCCESS.getCode());
        } else {
            return new RestResult<>(EnumError.ORACLE_CODE.getCode(), "oracle error code = " + result.getCode(), activity.getId());
        }
    }

    private OracleResponseVo<ActivityCreateOracleResultVo> createOracle(Activity activity) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type","application/json;charset=UTF-8");
        ActivityCreateOracleVo activityOracleVo = new ActivityCreateOracleVo();
        activityOracleVo.initFromActivity(activity);
        HttpEntity<ActivityCreateOracleVo> requestEntity = new HttpEntity<>(activityOracleVo, requestHeaders);
        return restTemplate.exchange(oracleUrl + "oracles/create",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<OracleResponseVo<ActivityCreateOracleResultVo>>() {}).getBody();
    }
}
