package com.djk.utils;

import java.io.Serializable;

import com.djk.autoconfigure.RedisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;


/**
 * redis 工具类
 *
 * @author djk
 */
public class RedisMap {
    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMap.class);
    /**
     * 注入redis的模板
     */
    private static RedisTemplate<String, Serializable> redisTemplate = SpringContextHolder.getBean("redisTemplate");

    /**
     * 注入redis配置文件
     */
    private static RedisProperties redisProperties = SpringContextHolder.getBean(RedisProperties.class);

    /**
     * 是否需要redis
     */
    private static boolean isNeedRedis = redisProperties.isUseRedis();


    /**
     * 根据key删除缓存
     *
     * @param key
     * @return
     */
    public static void delete(final String key) {
        // 如果redis 不需要 则直接返回
        if (!isNeedRedis) {
            LOGGER.info("Do not need use redis :{}", isNeedRedis);
            return;
        }
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            LOGGER.error("Delete cache fail and key : " + key);
        }
    }

    /**
     * 保存数据到redis中
     */
    public static boolean put(final String key, final Serializable value) {
        // 如果redis 不需要 则直接返回
        if (!isNeedRedis) {
            LOGGER.info("Do not need use redis :{}", isNeedRedis);
            return true;
        }

        try {

            RedisCallback<Boolean> redisCallback = connection -> {
                connection.set(redisTemplate.getStringSerializer().serialize(key), new JdkSerializationRedisSerializer().serialize(value));
                return true;
            };

            return redisTemplate.execute(redisCallback);
        } catch (Exception e) {
            LOGGER.error("Put value to redis fail...", e);
        }

        return false;
    }

    /**
     * 从redis 中查询数据
     */
    public static Object get(final String key) {
        // 如果redis 不需要 则直接返回
        if (!isNeedRedis) {

            return null;
        }

        try {

            RedisCallback<Object> redisCallback = connection ->
                    new JdkSerializationRedisSerializer().deserialize(connection.get(redisTemplate.getStringSerializer().serialize(key)));
            return redisTemplate.execute(redisCallback);
        } catch (Exception e) {
            LOGGER.error("Get value from  redis fail...", e);
        }
        return null;
    }
}
