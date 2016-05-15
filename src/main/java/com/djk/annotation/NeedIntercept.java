package com.djk.annotation;

import java.lang.annotation.*;

/**
 * Created by dujinkai on 2016/5/15.
 * 自定义注解需要被拦截器拦截
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedIntercept {

}
