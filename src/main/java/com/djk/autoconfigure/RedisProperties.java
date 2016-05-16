package com.djk.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dujinkai on 2016/5/15.
 * <p>
 * redis 的配置文件
 */
@ConfigurationProperties(prefix = "djk.redis")
public class RedisProperties {

    /**
     * 是否使用标识
     */
    private String useFlag;

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }

    /**
     * 是否使用redis
     *
     * @return
     */
    public boolean isUseRedis() {
        return "true".equalsIgnoreCase(useFlag) ? true : false;
    }

}
