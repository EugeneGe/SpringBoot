package com.gly.springboot.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author EugeneGe
 * @date 2022-08-19 16:57
 */
@Configuration
public class RedisConfig {

    /**
     *乱码问题解决 方式一
     * 在使用时，规定redisTemplate的类型
     * @Autowired
     * private RedisTemplate<String,String> redisTemplate
     *
     * 方式三
     * 使用 StringRedisTemplate 而不是使用 RedisTemplate
     */

    /**
     * redis key 出现乱码问题解决 方式二
     *
     * @param factory
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(redisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(redisSerializer);
        //key haspmap序列化
        template.setHashKeySerializer(redisSerializer);

        return template;
    }
}
