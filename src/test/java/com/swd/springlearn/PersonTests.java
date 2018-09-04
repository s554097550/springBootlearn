package com.swd.springlearn;

import com.swd.springlearn.entity.Person;
import com.swd.springlearn.mapper.PersonMapper;
import com.swd.springlearn.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringlearnApplication.class)
public class PersonTests {
    private static final Logger log = LoggerFactory.getLogger(PersonTests.class);
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void test1() throws Exception{
        final Person person = personRepository.save(new Person("swd2","123"));
        log.info("[添加成功]-[{}]",person);
        final List<Person> persons = personRepository.findAllByUserName("swd");
        log.info("[名称查询]-[{}]",persons);
        PageRequest pageable = PageRequest.of(0,10, Sort.by(Sort.Order.desc("userName")));
        final Page<Person> persons2 = personRepository.findAll(pageable);
        log.info("[分页+排序+查询所有] - [{}]", persons2.getContent());
    }

    @Test
    public void test2() throws Exception{
        final int row1 = personMapper.insert(new Person("swd3","1234"));
        log.info("[添加结果]-[{}]",row1);
        final List<Person> persons = personMapper.findPersonByName("swd");
        log.info("[名称查询]-[{}]",persons);
    }

}
