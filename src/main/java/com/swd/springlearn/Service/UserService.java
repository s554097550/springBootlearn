package com.swd.springlearn.service;

import com.swd.springlearn.dao.UserDao;
import com.swd.springlearn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAllUser(){
        return userDao.findAllUser();
    }

    public User findUserById(Integer id){
        return userDao.findUserById(id);
    }

    public int addUser(User user){
        return userDao.addUser(user);
    }

    public int delUserById(Integer id){
        return userDao.delUserById(id);
    }

    public int editUser(Integer id, User user){
        return  userDao.editUser(id,user);
    }
}
