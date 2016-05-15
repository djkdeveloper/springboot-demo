package com.djk.utils;

/**
 * Created by dujinkai on 2016/5/15.
 * 自定义异常
 */
public final class CustomException extends RuntimeException {


    /**
     * 失败信息
     */
    private final String message;

    /**
     * 处理建议
     */
    private final String suggestion;


    public CustomException(String message, String suggestion, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.suggestion = suggestion;
    }


    public CustomException(String message, String suggestion) {
        super(message);
        this.message = message;
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        return suggestion;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
