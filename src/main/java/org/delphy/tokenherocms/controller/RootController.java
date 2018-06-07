package org.delphy.tokenherocms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.annotation.RequestLimit;
import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.domain.AdminUserManager;
import org.delphy.tokenherocms.domain.UserManager;
import org.delphy.tokenherocms.entity.User;
import org.delphy.tokenherocms.pojo.AdminCache;
import org.delphy.tokenherocms.util.RequestUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author mutouji
 */
@Slf4j
@Api(description = "通用接口")
@RestController
public class RootController {
    private AdminUserManager adminUserManager;
    private UserManager userManager;

    public RootController(@Autowired AdminUserManager adminUserManager,
                          @Autowired UserManager userManager) {
        this.adminUserManager = adminUserManager;
        this.userManager = userManager;
    }

    @ApiOperation(value="登陆")
    @PostMapping("/login")
    @RequestLimit(count=10)
    public RestResult<String> login(@RequestBody SignInRequest signInRequest, HttpServletRequest request) {
        RequestUtil.logRequestIpAndUrl(request);
        if (signInRequest == null) {
            return new RestResult<>(1101, "param wrong format");
        }
        return adminUserManager.login(signInRequest.getAccount(), signInRequest.getPassword());
    }

    @ApiOperation(value="获取登陆")
    @GetMapping("/login")
    public RestResult<AdminCache> getAdmin(@RequestHeader String sid) {
        return adminUserManager.getAdmin(sid);
    }

    @ApiOperation("获取按size分页后第page页上的用户")
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

    @Data
    static class SignInRequest {
        private final String password = null;
        private final String account = null;
    }


}
