package org.delphy.tokenherocms.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.delphy.tokenherocms.annotation.RequestLimit;
import org.delphy.tokenherocms.exception.RequestLimitException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author mutouji
 */
@Slf4j
@Aspect
@Component
public class RequestLimitContract {
    private Map<String, Integer> redisTemplate = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(8,
            new BasicThreadFactory.Builder().namingPattern("request-limit-pool-%d").daemon(true).build());

    @Before("within(org.delphy.tokenherocms.controller.*) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                    break;
                }
            }
            if (request == null) {
                throw new RequestLimitException("方法中缺失HttpServletRequest参数");
            }
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
                redisTemplate.put(key, 1);
            } else {
                redisTemplate.put(key, redisTemplate.get(key) + 1);
            }
            int count = redisTemplate.get(key);
            if (count > 0) {
                scheduledExecutorService.schedule(() -> redisTemplate.remove(key), limit.time(), TimeUnit.MILLISECONDS);
            }
            if (count > limit.count()) {
                log.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            log.error("发生异常", e);
        }
    }
}
