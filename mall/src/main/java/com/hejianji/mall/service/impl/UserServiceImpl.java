package com.hejianji.mall.service.impl;

import com.hejianji.mall.dao.UserMapper;
import com.hejianji.mall.enums.ResponceEnum;
import com.hejianji.mall.enums.RoleEnum;
import com.hejianji.mall.pojo.User;
import com.hejianji.mall.service.IUserService;
import com.hejianji.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1.注册
     *
     * @param user
     */
    @Override
    public ResponseVo register(User user) {
        //1.username不能重复
        int countByUsername=userMapper.countByUsername(user.getUsername());
        if(countByUsername>0){
            return ResponseVo.error(ResponceEnum.USERNAME_EXIST);
        }

        //2.emain不能重复
        int countByEmail=userMapper.countByEmail(user.getEmail());
        if(countByEmail>0){
            return ResponseVo.error(ResponceEnum.EMAIL_EXIST);
        }

        //3.需要给默认的管理员权限
        user.setRole(RoleEnum.CUSTOMER.getCode());


        //md5摘要算法（spring自带）
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        int resultcode=userMapper.insertSelective(user);
        if(resultcode==0){
            throw new RuntimeException("插入失败");
        }

        //写入数据库   ERROR(-1, "服务端错误"),
        int ResultCount=userMapper.insertSelective(user);
        if(ResultCount>0){
            return ResponseVo.error(ResponceEnum.ERROR);
        }

        return ResponseVo.successByMsg();




    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);

        if(user==null){
            return ResponseVo.error(ResponceEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))){
            return ResponseVo.error(ResponceEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPassword("");
        return ResponseVo.success(user);


    }
}
