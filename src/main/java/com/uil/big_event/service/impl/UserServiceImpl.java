package com.uil.big_event.service.impl;

import com.uil.big_event.utils.Md5Util;
import com.uil.big_event.mapper.UserMapper;
import com.uil.big_event.pojo.User;
import com.uil.big_event.service.UserService;
import com.uil.big_event.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user){
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(id,avatarUrl);
    }

    @Override
    public void updatePwd(String newPwd){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(id,Md5Util.getMD5String(newPwd));
    }
}
