package com.djk.service.impl;

import com.djk.service.HellWorldService;
import com.djk.utils.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dujinkai on 2016/5/15.
 */
@Service
public class HelloWorldServiceImpl implements HellWorldService {

    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

    /**
     * 缓存map  key 是 参数  值是 这个接口请求的这个参数的最新时间
     */
    private static ConcurrentMap<String, AtomicLong> lastAlarmMap = new ConcurrentHashMap<String, AtomicLong>();

    @Override
    public String sayHello(String name) {
        LOGGER.info("Receive request and name :" + name);
        return "HelloWorld :" + name;
    }

    @Override
    public String limitSayHello(String name) {

        AtomicLong nanos = new AtomicLong(System.nanoTime());
        AtomicLong lastTime = lastAlarmMap.putIfAbsent(name, nanos);

        long lastNanos;

        //5分钟内只发一次
        long interval = TimeUnit.MINUTES.toNanos(5);

        if (lastTime == null || (System.nanoTime() - (lastNanos = lastTime.get()) > interval
                && lastTime.compareAndSet(lastNanos, nanos.get()))) {
            return "Hello :" + name;
        } else {
            LOGGER.error("time is not enough...");
            return "fail..";
        }
    }

    @Override
    public void testerror() {
        throw new CustomException("SystemError..", "NoSuggest");
    }
}
