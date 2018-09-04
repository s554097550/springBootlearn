package com.swd.springlearn.dao;

import com.swd.springlearn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAllUser(){
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserById(Integer id){
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},User.class);
    }

    public int addUser(User user){
        String sql = "insert into user(name,age,phone,email) values(?,?,?,?)";
        return jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getPhone(),user.getEmail());
    }

    public int delUserById(Integer id){
        String sql = "detele from user where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int editUser(Integer id, User user){
        // 根据主键ID修改用户信息
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), id);
    }
}
