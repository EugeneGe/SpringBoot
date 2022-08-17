package com.gly.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * redis配置类
 * 使用@Repository注解是spring自带的,需要在启动类添加@Mapperscan以表明mapper包的位置
 *
 * @author: EugeneGe
 * @date: 2021-05-25 17:34
 */
@Repository
@Component
public class RedisDao {

    /**
     * redis 生存时间 秒 此次设置两小时
     */
    private static Integer LEFT_TIME = 60 * 60 * 2;

    /**
     * redis 删除时间 秒 此次设置两小时
     */
    private static Integer DELETE_TIME = 60 * 60 * 2;

//    @Autowired
//    private RedisTemplate template;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 将key存入缓存中,并设置生存时间
     *
     * @param key
     * @param value
     */
    public void setKeyByTime(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, LEFT_TIME, TimeUnit.SECONDS);
    }

    /**
     * 将key存入缓存中,不设置时间
     *
     * @param key
     * @param value
     */
    public void setKey(String key, String value) {
        //向redis里存入数据
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 检测缓存中的值和存储时间,刷新
     * 通过key,此处是随机UUID,获得剩余存储时间.
     * 通过key获得储存的数据value.
     * 判断,若value为null,或剩余时间为-1,则返回value即null,此处表示未获得数据,即未登陆或登陆失效.
     * 若value不为null且剩余时间不为-1,
     * 刷新,重新将UUID作为key,将信息作为value,设置存储时间,重新存入redis中.
     *
     * @param key 此处为随机生成的UUID
     * @return 返回result, null则未登陆或失效,
     */
    public String getValueRefresh(String key) {
        Long expire = stringRedisTemplate.getExpire(key);
        String result = stringRedisTemplate.opsForValue().get(key);
        if (result == null || expire == -1L) {
            return result;
        }
        stringRedisTemplate.opsForValue().set(key, result, LEFT_TIME, TimeUnit.SECONDS);
        return result;
    }

    /**
     * 删除redis中存入的key
     *
     * @param key
     */
    public Boolean deleteValue(String key) {
        Boolean aBoolean = stringRedisTemplate.delete(key);
        return aBoolean;
    }
}
