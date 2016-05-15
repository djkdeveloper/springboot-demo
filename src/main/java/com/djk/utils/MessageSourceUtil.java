package com.djk.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Created by dujinkai on 2016/5/15.
 * 消息资源工具类
 */
public final class MessageSourceUtil {

    private MessageSourceUtil() {
    }

    /**
     * 获得消息源对象
     */
    private static MessageSource messageSource = SpringContextHolder.getBean(MessageSource.class);

    /**
     * 根据资源编码获取对应对国际化资源信息
     * - 适用于无参数的简单消息资源
     *
     * @param code
     * @return
     */
    public static String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return null;
        }
    }

}
