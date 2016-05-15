package com.djk.utils;

import com.djk.bean.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 2016/5/15.
 * 全局异常处理器
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 自身业务异常
     * - HTTP 500
     * - 服务器内部错误
     */
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<BaseResponse> serviceExceptionHandler(HttpServletRequest request, CustomException ex) throws Exception {
        LOGGER.error("[SERVER_ERROR] do [{}] on [{}] failed. {}: {}, {}", request.getMethod(), request.getRequestURL(),
                ex.getClass(), ex.getMessage(), ex.getSuggestion());

        return ResponseEntity.badRequest().body(new BaseResponse<String>(false, MessageSourceUtil.getMessage("0004"), ex.getMessage()));
    }

    /**
     * 运行时异常
     * - HTTP 500
     * - 服务器内部错误
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<BaseResponse> serviceExceptionHandler(HttpServletRequest request, RuntimeException ex) throws Exception {
        LOGGER.error("[SERVER_ERROR] do [{}] on [{}] failed. {}: {}", request.getMethod(), request.getRequestURL(),
                ex.getClass(), ex.getMessage());

        return ResponseEntity.badRequest().body(new BaseResponse<String>(false, MessageSourceUtil.getMessage("0004"), ex.getMessage()));
    }

}
