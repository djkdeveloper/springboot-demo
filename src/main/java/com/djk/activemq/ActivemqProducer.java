package com.djk.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by dujinkai on 2016/5/15.
 * activemq 生产者
 */
@Component
public class ActivemqProducer {

    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivemqProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    private Long count = 0L;

    /**
     * 生产
     */
    @Scheduled(fixedRate = 60000)
    public void produce() {
        LOGGER.info("Begin to  produce message to mq....");
        jmsTemplate.convertAndSend(topic, "test" + count++);
    }

}
