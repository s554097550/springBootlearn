package com.swd.springlearn.annotation;

import java.lang.annotation.*;

/**
 * 锁的注解
 * @author swd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {
    String key() default "";

    /**
     * 过期时间在redis中有用
     */
    int expire() default 5;
}
