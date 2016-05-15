package com.djk.autoconfigure;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by dujinkai on 2016/5/15.
 * jms自动配置
 */
@Configuration
@EnableConfigurationProperties(ActiveMqTopicProperties.class)
@EnableJms
public class JmsAutoConfiguration {

    @Autowired
    private ActiveMqTopicProperties activeMqTopicProperties;

    /**
     * jms 为P2P模式的时候
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(activeMqTopicProperties.getTopic());
    }

    /**
     * jms为发布订阅模式的时候
     *
     * @return
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(activeMqTopicProperties.getTopic());
    }

}
