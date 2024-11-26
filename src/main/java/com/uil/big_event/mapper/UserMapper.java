package com.uil.big_event.mapper;


import com.uil.big_event.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //@Select("select * from user where username = #{username}")
    User findByUsername(String username);

    //@Insert("insert into user(username,password,create_time,update_time)" +
    //        " values(#{username},#{password},now(),now())")
    void add(String username, String password);

    void update(User user);

    void updateAvatar(Integer id,String avatarUrl);

    void updatePwd(Integer id, String newPwd);
}
