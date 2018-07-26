package org.delphy.tokenherocms.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.pojo.ForecastVo;
import org.delphy.tokenherocms.service.UserService;
import org.delphy.tokenherocms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mutouji
 */
@Slf4j
@Api(tags = {"用户"}, description = "用户接口")
@RestController
public class UserController {
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "获取按size分页后第page页上的用户", notes = "page是索引，size是分页大小")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="page",value="分页索引", paramType = "query", dataType="int", required = true, defaultValue = "0"),
            @ApiImplicitParam(name="size",value="分页大小", paramType = "query", dataType="int", required = true, defaultValue = "10"),
            @ApiImplicitParam(name="name",value="用户昵称", paramType = "query", dataType="string")
    })
    @GetMapping("/users")
    public RestResult<List<User>> getUsers(Integer page, Integer size, String name) {
        if (page != null && size != null) {
            return userService.getUsers(page, size, name);
        }
        return new RestResult<>(1102, "miss page(index) or size param");
    }

    @ApiOperation("获取用户总数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="name",value="用户名", paramType = "query", dataType="string")
    })
    @GetMapping("/userscount")
    public RestResult<Long> getUsersCount(String name) {
        return userService.getUsersCount(name);
    }

    @ApiOperation("获取某用户的所有预测记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="id",value="user的id", paramType = "path", dataType="string", required = true, defaultValue = "15233590196834402"),
            @ApiImplicitParam(name="page",value="分页索引", paramType = "query", dataType="int", required = true, defaultValue = "0"),
            @ApiImplicitParam(name="size",value="分页大小", paramType = "query", dataType="int", required = true, defaultValue = "10")
    })
    @GetMapping("/users/{id}/forecasts")
    public RestResult<List<ForecastVo>> getUserForecasts(@PathVariable String id, Integer page, Integer size) {
        if (page != null && size != null) {
            return userService.getUserForecasts(id, page, size);
        }
        return new RestResult<>(1102, "miss page(index) or size param");
    }

    @ApiOperation("获取某用户预测记录的总数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="id",value="user的id", paramType = "path", dataType="string", required = true, defaultValue = "15233590196834402")
    })
    @GetMapping("/users/{id}/forecastscount")
    public RestResult<Long> getUserForecastsCount(@PathVariable String id) {
        return userService.getUserForecastsCount(id);
    }
}