package com.djk.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dujinkai on 2016/5/15.
 * <p>
 * ActiveMq 主题的配置文件
 */
@ConfigurationProperties(prefix = "djk.activemq")
public class ActiveMqTopicProperties {

    /**
     * 主题
     */
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    @Override
    public String toString() {
        return "ActiveMqTopicProperties{" +
                "topic='" + topic + '\'' +
                '}';
    }
}
