package org.delphy.tokenherocms.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.AdminUser;
import org.delphy.tokenherocms.pojo.AdminCache;
import org.delphy.tokenherocms.repository.IAdminUserRepository;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mutouji
 */
@Slf4j
@Service
public class AdminUserManager {
    private IAdminUserRepository iAdminUserRepository;
    private RedissonClient redissonClient;

    public AdminUserManager(@Autowired IAdminUserRepository iAdminUserRepository,
                          @Autowired RedissonClient redissonClient) {
        this.iAdminUserRepository = iAdminUserRepository;
        this.redissonClient = redissonClient;
    }

    public RestResult<String> login(String account, String password) {
        AdminUser adminUser = iAdminUserRepository.findOneByAccount(account);
        if (adminUser == null) {
            return new RestResult<>(1001, "account not exist");
        }
        String pwd = new String (Base64.decodeBase64(adminUser.getPwd()));
        if (adminUser.getAccount().equals(account)
                && password.equals(pwd)) {
            String uniqueId = genUniqueId();
            if (saveAccessToken(uniqueId, adminUser)) {
                return new RestResult<>(0, "success", uniqueId);
            } else {
                return new RestResult<>(1003, "cache service error");
            }
        }

        return new RestResult<>(1002, "password error");
    }

    public RestResult<AdminCache> getAdmin(String sid) {
        AdminCache adminCache = getAdminCacheByToken(sid);
        if (adminCache == null) {
            return new RestResult<>(1004, "非登陆状态");
        } else {
            return new RestResult<>(0, "success", adminCache);
        }
    }

    private AdminCache getAdminCacheByToken(final String uniqueId) {
        RBucket<AdminCache> rBucket = redissonClient.getBucket(Constant.ADMIN_PREFIX + uniqueId);
        log.info("do getAdminCacheByToken");
        return rBucket.get();
    }

    private boolean saveAccessToken(String uniqueId, AdminUser adminUser) {
        RBucket<AdminCache> rBucket = redissonClient.getBucket(Constant.ADMIN_PREFIX + uniqueId);
        AdminCache adminCache = new AdminCache();
        adminCache.setAccount(adminUser.getAccount());
        adminCache.setToken(uniqueId);
        return rBucket.trySet(adminCache, 5, TimeUnit.MINUTES);
    }

    private String genUniqueId() {
        return UUID.randomUUID().toString();
    }
}
