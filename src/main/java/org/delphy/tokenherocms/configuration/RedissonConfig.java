package org.delphy.tokenherocms.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 属性值可以使用@Value注释直接注入到bean中，
// 可以通过Spring的Environment抽象来访问，
// 也可以通过@ConfigurationProperties绑定到结构化对象

// 环境变量：表示类似的环境变量PATH，USER等等，存储在MapPropertySource;
// 系统属性：表示JVM的属性，类似java.vm.version，path.separator并且存储在类中OriginAwareSystemEnvironmentPropertySource;
// Servlet配置属性;
// 随机值属性源：用于生成随机值;
// 应用程序配置属性：在application.yml和中定义的属性application.properties;

//@PropertySource("classpath:pipeline.properties")
//@Configuration
//@ConfigurationProperties(prefix = "pipeline")

//@PropertySource("classpath:pipeline.yml")
@Configuration
@EnableCaching // for cache
public class RedissonConfig {
    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson(@Value("classpath:redisson/redisson-single.yaml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        // 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
        config.put("testMap", new CacheConfig(24*60*1000, 12*60*1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }

    // Spring Cache也可以通过JSON或YAML配置文件来配置
//    @Bean
//    CacheManager cacheManager(RedissonClient redissonClient) throws IOException {
//        return new RedissonSpringCacheManager(redissonClient, "classpath:/cache-config.json");
//    }

    ////////////////////////////////////////////////////////////////
    // below is how to create RedissonClient by code
    // redisson master slave
//    RedissonClient redissonMasterSlave() {
//        Config config = new Config();
//        config.useMasterSlaveServers()
//                //可以用"rediss://"来启用SSL连接
//                .setMasterAddress("redis://127.0.0.1:6379")
//                .addSlaveAddress("redis://127.0.0.1:6389", "redis://127.0.0.1:6332", "redis://127.0.0.1:6419")
//                .addSlaveAddress("redis://127.0.0.1:6399");
//
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
//    // sentinel
//    RedissonClient redissonSentinel() {
//        Config config = new Config();
//        config.useSentinelServers()
//                .setMasterName("mymaster")
//                //可以用"rediss://"来启用SSL连接
//                .addSentinelAddress("redis://127.0.0.1:26389", "redis://127.0.0.1:26379")
//                .addSentinelAddress("redis://127.0.0.1:26319");
//
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
//    // single
//    RedissonClient redissonSingle() {
//        // 默认连接地址 127.0.0.1:6379
//        // RedissonClient redisson = Redisson.create();
//
//        Config config = new Config();
//        config.useSingleServer().setAddress("127.0.0.1:6379");
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
//    // 云托管模式
//    RedissonClient redissonReplicate() {
//        Config config = new Config();
//        config.useReplicatedServers()
//                .setScanInterval(2000) // 主节点变化扫描间隔时间
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
//                .addNodeAddress("redis://127.0.0.1:7002");
//
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
//    // 集群模式
//    RedissonClient redissonCluster() {
//        Config config = new Config();
//        config.useClusterServers()
//                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
//                .addNodeAddress("redis://127.0.0.1:7002");
//
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
}
