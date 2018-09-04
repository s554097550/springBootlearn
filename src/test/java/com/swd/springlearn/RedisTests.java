package com.swd.springlearn;

import com.swd.springlearn.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author swd
 * @ClassName: RedisTests
 * @ProjectName springlearn
 * @Description: TODO
 * @date 2018/9/419:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringlearnApplication.class)
public class RedisTests {
    private static final Logger log = LoggerFactory.getLogger(RedisTests.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;

    @Test
    public void get(){
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0,1000).forEach(i->executorService.execute(()->stringRedisTemplate.opsForValue().increment("kk",1)));
        stringRedisTemplate.opsForValue().set("k1","v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);
        String key = "swd:person:1";
        redisTemplate.opsForValue().set(key,new Person("swd","redis"));
        final Person pserson = (Person) redisTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", pserson);
    }
}
