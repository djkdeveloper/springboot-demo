package com.djk.controller;

import com.djk.annotation.NeedIntercept;
import com.djk.bean.BaseResponse;
import com.djk.utils.MessageSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dujinkai on 2016/5/15.
 * 异常控制器，抛弃spring默认的白色页面，统一采用json返回
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class SimpleErrorController implements ErrorController {

    /**
     * 调试日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleErrorController.class);

    private final ErrorAttributes errorAttributes;

    private final ErrorProperties errorProperties;

    @Autowired
    public SimpleErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        this.errorAttributes = errorAttributes;
        this.errorProperties = serverProperties.getError();
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }


    @RequestMapping
    public ResponseEntity<BaseResponse> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request));
        LOGGER.error("[Failed] method:[{}] status:[{}] error:[{}] path:[{}]", request.getMethod(), body.get("status"), body.get("error"), body.get("path"));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(false, MessageSourceUtil.getMessage("0001"), ""));
    }

    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = this.errorProperties.getIncludeStacktrace();
        return include == ErrorProperties.IncludeStacktrace.ALWAYS || include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM && getTraceParameter(request);
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equals(parameter.toLowerCase());
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}
