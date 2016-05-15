package com.djk.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by dujinkai on 2016/5/15.
 * <p>
 * activqmq 的消费者
 */
@Component
public class ActivemqConsumer {

    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivemqConsumer.class);

    @JmsListener(destination = "whxddd")
    public void receiveQueue(String text) {
        LOGGER.info("Receive mq..." + text);
    }

}
