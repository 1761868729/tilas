package org.example.tilas.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tilas.pojo.Emp;
import org.example.tilas.pojo.Result;
import org.example.tilas.service.EmpService;
import org.example.tilas.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}",emp);
        Emp e = empService.login(emp);
        //登录成功，生成令牌，下发令牌
        if(e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username",e.getUsername());
            JwtUtils.generateJwt(claims);
            //JWT令牌包含当前登录的员工信息
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        //登陆失败，返回错误信息
        return Result.error("用户名密码错误");
    }

}
