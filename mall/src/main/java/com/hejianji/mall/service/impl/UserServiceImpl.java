package com.hejianji.mall.service.impl;

import com.hejianji.mall.dao.UserMapper;
import com.hejianji.mall.pojo.User;
import com.hejianji.mall.service.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements IUserSevice {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1.注册
     *
     * @param user
     */
    @Override
    public void register(User user) {
        //1.username不能重复
        int countByUsername=userMapper.countByUsername(user.getUsername());
        if(countByUsername>0){
            throw new RuntimeException("username重复");
        }

        //2.emain不能重复
        int countByEmail=userMapper.countByEmail(user.getEmail());
        if(countByEmail>0){
            throw new RuntimeException("username重复");
        }

        //md5摘要算法（spring自带）
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        int resultcode=userMapper.insertSelective(user);
        if(resultcode==0){
            throw new RuntimeException("插入失败");
        }



    }
}
