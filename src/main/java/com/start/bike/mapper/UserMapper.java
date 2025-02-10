package com.start.bike.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.start.bike.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Object selectUser(String username, String password);
    Boolean isExistUser(String username);
    void insertUser(User user);
}
