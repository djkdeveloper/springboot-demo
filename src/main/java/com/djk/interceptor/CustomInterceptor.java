package com.djk.interceptor;

import com.djk.annotation.NeedIntercept;
import com.djk.utils.MessageSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dujinkai on 2016/5/15.
 * 自定义拦截器
 */
public class CustomInterceptor extends HandlerInterceptorAdapter {

    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        /**
         * 判断拦截的是否是方法
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NeedIntercept needIntercept = handlerMethod.getMethodAnnotation(NeedIntercept.class);

            if (null != needIntercept) {
                LOGGER.error(MessageSourceUtil.getMessage("0002"));
                return false;
            }

            LOGGER.info(MessageSourceUtil.getMessage("0003"));
        }
        return true;
    }
}
