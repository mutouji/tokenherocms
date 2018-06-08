package org.delphy.tokenherocms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.domain.UserManager;
import org.delphy.tokenherocms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mutouji
 */
@Slf4j
@Api(tags = {"用户"}, description = "用户接口")
@RestController
public class UserController {
    private UserManager userManager;

    public UserController(@Autowired UserManager userManager) {
        this.userManager = userManager;
    }

    @ApiOperation(value = "获取按size分页后第page页上的用户", notes = "page是索引，size是分页大小")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page",value="分页索引", paramType = "query", dataType="int", required = true, defaultValue = "3"),
        @ApiImplicitParam(name="size",value="分页大小", paramType = "query", dataType="int", required = true, defaultValue = "10")
    })
    @GetMapping("/users")
    public RestResult<List<User>> getUsers(@RequestHeader String sid, Integer page, Integer size) {
        log.info("page=" + page + ", size=" + size);
        if (page != null && size != null) {
            return userManager.getUsers(page, size);
        }
        return new RestResult<>(1102, "miss page(index) or size param");
    }

    @ApiOperation("获取用户总数")
    @GetMapping("/userscount")
    public RestResult<Long> getUsersCount(@RequestHeader String sid) {
        return userManager.getUsersCount();
    }
}