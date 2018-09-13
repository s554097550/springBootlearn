package com.swd.springlearn.controller;

import com.swd.springlearn.annotation.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swd
 * @ClassName: InterceptorController
 * @ProjectName springlearn
 * @Description: TODO
 * @date 2018/9/1316:55
 */
@RestController
@RequestMapping("/interception")
public class InterceptorController {
    @LocalLock(key = "book:arg[0]")
    @GetMapping
    public String query(String token){
        return "success-"+token;
    }
}
