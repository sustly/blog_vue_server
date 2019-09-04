package com.sustly.dao;

import com.sustly.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:13
 */
@Mapper
public interface UserDao {

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into user(username, password, create_time, last_login_time) values (#{username}, #{password}, #{createTime}, #{lastLoginTime})")
    void save(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsernameAndPassword(String username, String password);
}
