package com.yang.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedissonStudyApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RedissonStudyApplication.class, args);
        
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setCodec(new org.redisson.codec.JsonJacksonCodec()); // 使用 Jackson Codec
        
        // Redisson 核心类
        RedissonClient redissonClient = Redisson.create(config);
        
        // 3. 获取 RBucket 对象
        RBucket<String> bucket = redissonClient.getBucket("myKey");
        
        // 4. 设置值
        bucket.set("myValue");
        
        // 验证存储是否成功
        System.out.println("Value set in Redis: " + bucket.get());
        
        redissonClient.shutdown();
        
    }
    
}
