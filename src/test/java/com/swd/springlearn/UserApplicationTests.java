package com.swd.springlearn;

import com.swd.springlearn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringlearnApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;

    @Test
    public void addUserTest()throws Exception{
        testRestTemplate.postForEntity("http://localhost:9090/test/user/addUser",new User("宋文东",26,"15739575303","554097550@qq.com"),String.class);
        testRestTemplate.postForEntity("http://localhost:9090/test/user/addUser",new User("宋文东2",26,"15739575303","554097550@qq.com"),String.class);
        testRestTemplate.postForEntity("http://localhost:9090/test/user/addUser",new User("宋文东3",26,"15739575303","554097550@qq.com"),String.class);
    }
}
