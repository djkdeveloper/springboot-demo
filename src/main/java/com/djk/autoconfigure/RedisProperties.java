package com.djk.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dujinkai on 2016/5/15.
 * <p>
 * redis 的配置文件
 */
@ConfigurationProperties(prefix = "djk.redis")
public class RedisProperties {
}
