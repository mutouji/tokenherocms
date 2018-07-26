package org.delphy.tokenherocms.configuration;

import org.delphy.tokenherocms.handler.TokenInterceptor;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author mutouji
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private RedissonClient redissonClient;

    public WebConfig(@Autowired RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(redissonClient)).excludePathPatterns(
                "/login", "/error", "/**.html","/v2/api-docs","/swagger-resources/**","/webjars/**","/test/**");
        super.addInterceptors(registry);
    }
}
