package org.delphy.tokenherocms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.delphy.tokenherocms.annotation.RequestLimit;
import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.AdminUser;
import org.delphy.tokenherocms.util.EncryptUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(description = "通用接口")
@RestController
public class RootController {
    @Autowired
    RedissonClient redissonClient;

    @ApiOperation(value = "需要先获取sessionid才能登陆或注册")
    @GetMapping("/session")
    @RequestLimit(count=10,time=60000)
    public RestResult<String> getSessionId(HttpServletRequest request) {
        String sessionId = genSessionId();
        redissonClient.getBucket(Constant.SESSION_PREFIX + sessionId).set(9527, 50, TimeUnit.MINUTES);
        return new RestResult<String>(200, "获取成功", sessionId);
    }

    // 用用户名密码对sessionid,
    @ApiOperation(value="登陆")
    @PostMapping("/login")
    @RequestLimit(count=10, time=60000)
    public RestResult<String> login(@RequestHeader("sid") String sessionId, @RequestBody SignInRequest signInRequest, HttpServletRequest request) throws Exception {
        // 1. 检查sessiond是否有效
        RBucket rBucket = redissonClient.getBucket(Constant.SESSION_PREFIX + sessionId);
        if (rBucket.isExists()) {
            log.info("session is exist answer={0}, account={1}", signInRequest.getAnswer(), signInRequest.getAnswer());
        } else {
//            log.error("session id is not exist");
            throw new Exception("session id is not exist");
        }

        // 2. 检查用户名和签名是否有效，有效则登陆成功
        AdminUser adminUser = new AdminUser();
        String password = adminUser.getPwd(); // Base64.decode(adminUser.getPwd());
        byte[] sessionKey = EncryptUtil.hmacSHA256(sessionId.getBytes(), password.getBytes());
        byte[] serverAnswer = EncryptUtil.hmacSHA256(sessionId.getBytes(), sessionKey);
        String serverStr = Base64.encodeBase64String(serverAnswer);
        if (serverStr.equalsIgnoreCase(signInRequest.getAnswer())) {
            rBucket.expire(5, TimeUnit.MILLISECONDS);
            // 保存
        }
        // 3. 如果登陆成功，则在redis中记录已登陆状态，并设置免登陆时间。

        return null;
    }

    private String genSessionId() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    @Data
    static class SignInRequest {
        private final String answer = null;
        private final String account = null;
    }


}
