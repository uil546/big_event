package com.uil.big_event.controller;

import com.uil.big_event.pojo.Result;
import com.uil.big_event.pojo.User;
import com.uil.big_event.service.UserService;
import com.uil.big_event.utils.Md5Util;
import com.uil.big_event.utils.JwtUtil;
import com.uil.big_event.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")//搞清楚Post,Get和Put的差别
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByUsername(username);
        if(user == null){
            userService.register(username,password);
            return Result.success();
        }else{
            return Result.error("用户名已被使用！");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        User user = userService.findByUsername(username);
        if(user == null){
            return Result.error("用户名错误！");
        }

        if(Md5Util.getMD5String(password).equals(user.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误！");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatarUrl){//@RequestParam标注的参数是必传的
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("必填项不能为空！");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findByUsername(username);
        if(!Md5Util.getMD5String(oldPwd).equals(user.getPassword())){
            return Result.error("原密码不正确");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("确认密码与新密码不一致");
        }

        userService.updatePwd(newPwd);
        return Result.success();
    }
}
