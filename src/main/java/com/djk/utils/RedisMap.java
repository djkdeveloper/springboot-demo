package com.djk.utils;

import java.io.Serializable;

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
     * 是否需要redis
     */
    private static boolean isNeedRedis = true;


    /**
     * 根据key删除缓存
     *
     * @param key
     * @return
     */
    public static void delete(final String key) {
        // 如果redis 不需要 则直接返回
        if (!isNeedRedis) {
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
            return true;
        }

        try {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    connection.set(redisTemplate.getStringSerializer().serialize(key), new JdkSerializationRedisSerializer().serialize(value));
                    return true;
                }
            });
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
            return redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    return new JdkSerializationRedisSerializer().deserialize(connection.get(redisTemplate.getStringSerializer().serialize(key)));
                }
            });
        } catch (Exception e) {
            LOGGER.error("Get value from  redis fail...", e);
        }
        return null;
    }
}