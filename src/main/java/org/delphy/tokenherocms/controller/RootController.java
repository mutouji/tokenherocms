package org.delphy.tokenherocms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.annotation.RequestLimit;
import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.AdminUser;
import org.delphy.tokenherocms.pojo.AdminCache;
import org.delphy.tokenherocms.repository.IAdminUserRepository;
import org.delphy.tokenherocms.util.RequestUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mutouji
 */
@Slf4j
@Api(description = "通用接口")
@RestController
public class RootController {
    private IAdminUserRepository iAdminUserRepository;
    private RedissonClient redissonClient;

    public RootController(@Autowired IAdminUserRepository iAdminUserRepository,
                          @Autowired RedissonClient redissonClient) {
        this.iAdminUserRepository = iAdminUserRepository;
        this.redissonClient = redissonClient;
    }

    @ApiOperation(value="登陆")
    @PostMapping("/login")
    @RequestLimit(count=10)
    public RestResult<String> login(@RequestBody SignInRequest signInRequest, HttpServletRequest request) {
        RequestUtil.logRequestIpAndUrl(request);

        AdminUser adminUser = iAdminUserRepository.findOneByAccount(signInRequest.getAccount());
        if (adminUser == null) {
            return new RestResult<>(1001, "account not exist");
        }
        String password = new String (Base64.decodeBase64(adminUser.getPwd()));
        if (adminUser.getAccount().equals(signInRequest.getAccount())
                && signInRequest.getPassword().equals(password)) {
            String uniqueId = genUniqueId();
            if (saveAccessToken(uniqueId, adminUser)) {
                return new RestResult<>(0, "success", uniqueId);
            } else {
                return new RestResult<>(1003, "cache service error");
            }
        }

        return new RestResult<>(1002, "password error");
    }

    @ApiOperation(value="登陆")
    @GetMapping("/login")
    public RestResult<AdminCache> getAdmin(@RequestHeader String uniqueId) {
        AdminCache adminCache = getAdminCacheByToken(uniqueId);
        if (adminCache == null) {
            return new RestResult<>(1004, "非登陆状态");
        } else {
            return new RestResult<>(0, "success", adminCache);
        }
    }

//    @Cacheable(value = Constant.ADMIN_PREFIX + "#uniqueId", condition = "#result ne null")

    private AdminCache getAdminCacheByToken(final String uniqueId) {
        RBucket<AdminCache> rBucket = redissonClient.getBucket(Constant.ADMIN_PREFIX + uniqueId);
        log.info("do getAdminCacheByToken");
        return rBucket.get();
    }

    private String genUniqueId() {
        return UUID.randomUUID().toString();
    }

    private boolean saveAccessToken(String uniqueId, AdminUser adminUser) {
        RBucket<AdminCache> rBucket = redissonClient.getBucket(Constant.ADMIN_PREFIX + uniqueId);
        AdminCache adminCache = new AdminCache();
        adminCache.setAccount(adminUser.getAccount());
        adminCache.setToken(uniqueId);
        return rBucket.trySet(adminCache, 5, TimeUnit.MINUTES);
    }

    @Data
    static class SignInRequest {
        private final String password = null;
        private final String account = null;
    }


}
