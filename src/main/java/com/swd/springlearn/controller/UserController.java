package com.swd.springlearn.controller;

import com.swd.springlearn.Service.UserService;
import com.swd.springlearn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    @PostMapping("/addUser")
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public int delUserById(@PathVariable Integer id){
        return userService.delUserById(id);
    }

    @PutMapping("/{id}")
    public int editUser(@PathVariable Integer id, @RequestBody User user){
        return  userService.editUser(id,user);
    }
}
