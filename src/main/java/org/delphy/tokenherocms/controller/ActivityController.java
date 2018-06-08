package org.delphy.tokenherocms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.EnumError;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.Activity;
import org.delphy.tokenherocms.pojo.ActivityVo;
import org.delphy.tokenherocms.repository.IActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mutouji
 */
@Slf4j
@Api(value="活动",description = "活动相关接口",tags={"活动"})
@RestController
public class ActivityController {
    private IActivityRepository activityRepository;

    public ActivityController(@Autowired IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @ApiOperation(value="创建小程序活动", notes = "即微信版")
    @PostMapping("/wxactivity")
    public RestResult<String> createWXActivity(@RequestHeader String sid, ActivityVo vo) {
        Activity activity = new Activity();
        EnumError enumError = vo.initActivity(activity);
        if (enumError.getCode() == 0) {
            activityRepository.save(activity);
        }
        return new RestResult<>(enumError.getCode(), enumError.getMsg(), activity.getId());
    }
}
