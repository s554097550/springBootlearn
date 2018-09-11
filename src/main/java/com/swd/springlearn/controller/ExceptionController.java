package com.swd.springlearn.controller;

import com.swd.springlearn.Exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swd
 * @ClassName: ExceptionController
 * @ProjectName springlearn
 * @Description: 异常处理测试controller
 * @date 2018/9/1014:10
 */
@RestController
public class ExceptionController {
    @GetMapping("/testException")
    public String exceptionDeal(Integer num){
        if (num == null){
            throw new CustomException(400,"num不能为空");
        }
        int i = 10/num;
        return "result:"+i;
    }
}
