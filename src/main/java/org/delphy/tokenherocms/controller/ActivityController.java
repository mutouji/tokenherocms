package org.delphy.tokenherocms.controller;

import io.swagger.annotations.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.Activity;
import org.delphy.tokenherocms.pojo.ActivityVo;
import org.delphy.tokenherocms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mutouji
 */
@Slf4j
@Api(value="活动",description = "活动相关接口",tags={"活动"})
@RestController
public class ActivityController {
    private ActivityService activityService;

    public ActivityController(@Autowired ActivityService activityService) {
        this.activityService = activityService;
    }

    @ApiOperation(value="创建活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name = "mode", value = "0-微信小程序活动，1-H5活动", paramType = "query", dataType = "long", required = true, defaultValue = "0"),
    })
    @PostMapping("/activities")
    public RestResult<String> createActivity(@RequestBody ActivityVo vo, Long mode) {
        return activityService.createActivity(vo, mode);
    }

    @ApiOperation(value="获取一个活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="id",value="活动的id", paramType = "path", dataType="string", required = true, defaultValue = "15233597961200765")
    })
    @GetMapping("/activities/{id}")
    public RestResult<Activity> findOne(@PathVariable String id) {
        return activityService.findOne(id);
    }

    @ApiOperation(value="修改某个活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="id",value="活动的id", paramType = "path", dataType="string", required = true, defaultValue = "15233597961200765")
    })
    @PostMapping("/activities/{id}")
    public RestResult<String> updateOne(@PathVariable String id, @RequestBody ActivityVo vo) {
        return activityService.updateOne(id, vo);
    }

    @ApiOperation(value="手动设置某活动结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "headAer", dataType = "string", required = true),
            @ApiImplicitParam(name="id",value="活动的id", paramType = "path", dataType="string", required = true, defaultValue = "15233597961200765")
    })
    @PostMapping("/activities/{id}/result")
    public RestResult<String> updateOneResult(@PathVariable String id, @RequestBody ActivityResultRequest result) {
        if (result == null || result.getResult() == null || result.getResult() <= 0.0) {
            return new RestResult<>(1301, "wrong result");
        }
        return activityService.updateOneResult(id, result.getResult());
    }

    @ApiOperation(value="获取oracle结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
    })
    @GetMapping("/activityoracle/{id}/result")
    public RestResult<Double> getActivityOracleResult(@PathVariable String id) {
        return activityService.getActivityOracleResult(id);
    }

    @ApiModel(description = "手动设置结果的请求体")
    @Data
    static class ActivityResultRequest {
        @ApiModelProperty(value="result", required = true, example = "88.88")
        private final Double result = null;
    }

    @ApiOperation(value="删除某个小程序活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="id",value="活动的id", paramType = "path", dataType="string", required = true, defaultValue = "15233597961200765")
    })
    @PostMapping("/activities/delete/{id}")
    public RestResult<String> deleteOne(@PathVariable String id) {
        return activityService.deleteOne(id);
    }

    @ApiOperation(value="获取按size分页后第page页上的活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="status", value="0或不填获取全部，未开始1，进行中2，锁定中3，清算中4，已结束5", paramType = "query", dataType="int", defaultValue = "0"),
            @ApiImplicitParam(name="page", value="分页索引", paramType = "query", dataType="int", required = true, defaultValue = "0"),
            @ApiImplicitParam(name="size", value="分页大小", paramType = "query", dataType="int", required = true, defaultValue = "2"),
            @ApiImplicitParam(name = "mode", value = "0-微信小程序活动，1-H5活动", paramType = "query", dataType = "long", required = true, defaultValue = "0"),
    })
    @GetMapping("/activities")
    public RestResult<List<Activity>> getActivities(Integer status, Integer page, Integer size, Long mode) {
        return activityService.getActivities(status, page, size, mode);
    }

    @ApiOperation("获取用户总数")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name = "status", value = "0或不填获取全部，未开始1，进行中2，锁定中3，清算中4，已结束5", paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(name = "mode", value = "0-微信小程序活动，1-H5活动", paramType = "query", dataType = "long", required = true, defaultValue = "0"),
    })
    @GetMapping("/activitiescount")
    public RestResult<Long> getActivitiesCount(Integer status, Long mode) {
        return activityService.getActivitiesCount(status, mode);
    }
}
