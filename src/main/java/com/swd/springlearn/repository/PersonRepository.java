package com.swd.springlearn.repository;

import com.swd.springlearn.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author swd
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    /**
     * 按照名字查询所有用户
     * @param userName
     * @return 用户列表
     */
    List<Person> findAllByUserName(String userName);
}
