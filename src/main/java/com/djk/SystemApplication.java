package com.djk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.util.Locale;

/**
 * Created by dujinkai on 2016/5/15.
 * 系统启动类
 */
@EnableSwagger2
@SpringBootApplication
@EnableScheduling
public class SystemApplication {

    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemApplication.class);

    /**
     * 启动
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Environment env = SpringApplication.run(SystemApplication.class, args).getEnvironment();

        String port = env.getProperty("server.port", "8080");

        LOGGER.info(
                "Access URLs:\n----------------------------------------------------------\n\t"
                        + "Local: \t\thttp://127.0.0.1:{}\n\t"
                        + "External: \thttp://{}:{}\n----------------------------------------------------------",
                port, InetAddress.getLocalHost().getHostAddress(), port
        );
    }


    /**
     * 本地化设置
     */
    @Bean
    public LocaleResolver localeResolver() {

        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }
}
