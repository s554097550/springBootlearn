package com.swd.springlearn.interceptor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.swd.springlearn.exception.CustomException;
import com.swd.springlearn.annotation.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author swd
 * @ClassName: LockMethodInterceptor
 * @ProjectName springlearn
 * @Description: TODO
 * @date 2018/9/1315:58
 */
@Aspect
@Configuration
public class LockMethodInterceptor {
    private static final Cache<String,Object> CACHES = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(5, TimeUnit.SECONDS).build();

    @Around("execution(public * *(..)) && @annotation(com.swd.springlearn.annotation.LocalLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = getKey(localLock.key(), pjp.getArgs());
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                throw new CustomException(400, "请勿重复提交");
            }
            //如果是第一次请求就放入缓存
            CACHES.put(key, key);
        }
        Object result = null;
            try {
                result  = pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
            //    CACHES.invalidate(key);
            }
            return result;
    }

        /**
         * key的生成策略
         * @param keyExpress
         * @param args
         * @return
         */
        private String getKey (String keyExpress, Object[]args){
            for (int i = 0; i < args.length; i++) {
                keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
            }
            return keyExpress;
        }

}
