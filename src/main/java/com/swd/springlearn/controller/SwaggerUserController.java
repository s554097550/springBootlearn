package com.swd.springlearn.controller;

import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import com.swd.springlearn.entity.SwaggerUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author swd
 * @ClassName: SwaggerUserController
 * @ProjectName springlearn
 * @Description:
 * @date 2018/9/517:45
 */
@RestController
@RequestMapping("/swaggerUsers")
@Api(tags = "1.0",description = "用户管理",value = "用户管理")
public class SwaggerUserController {
    @GetMapping
    @ApiOperation(value = "条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",dataType = ApiDataType.STRING,paramType = ApiParamType.QUERY),
            @ApiImplicitParam(name = "passWord",value = "密码",dataType = ApiDataType.STRING,paramType = ApiParamType.QUERY)
    })
    public SwaggerUser query(String userName,String passWord){
        return new SwaggerUser(userName,passWord);
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "主键查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = ApiDataType.LONG,paramType = ApiParamType.PATH)
    })
    public SwaggerUser get(@PathVariable Long id){
        return new SwaggerUser(id,"swd1","123");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = ApiDataType.LONG,paramType = ApiParamType.PATH)
    })
    public void deleteUser(@PathVariable Long id){
        System.out.println("删除用户："+id);
    }

    @PostMapping
    @ApiOperation(value = "增加用户")
    public SwaggerUser addUser(@RequestBody SwaggerUser user){
        System.out.printf("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return user;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新用户")
    public SwaggerUser modUser(@PathVariable Long id,@RequestBody SwaggerUser user){
        System.out.printf("更新用户");
        return user;
    }
}
