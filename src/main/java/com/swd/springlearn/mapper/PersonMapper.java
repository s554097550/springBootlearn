package com.swd.springlearn.mapper;

import com.swd.springlearn.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 /**
 * @ClassName: PersonMapper
 * @ProjectName springlearn
 * @Description: 根据用户名查询用户 1.注解方式 2.xml方式
 * @author swd
 * @date 2018/9/4 17:00
 */
@Mapper
public interface PersonMapper {

    @Select("select * from t_person where user_name= #{userName}")
    List<Person> findPersonByName(String userName);

    int insert(Person person);
}
