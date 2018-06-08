package org.delphy.tokenherocms.handler;

import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.EnumError;
import org.delphy.tokenherocms.exception.DefaultException;
import org.delphy.tokenherocms.pojo.AdminCache;
import org.delphy.tokenherocms.util.RequestUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mutouji
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    private RedissonClient redissonClient;

    public TokenInterceptor(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RequestUtil.logRequestIpAndUrl(request);
        String sid  = request.getHeader("sid");
        RBucket<AdminCache> rBucket = redissonClient.getBucket(Constant.ADMIN_PREFIX + sid);
        if (rBucket == null || !rBucket.isExists()) {
            throw new DefaultException(EnumError.INCORRECT_TOKEN);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
