package com.hejianji.mall.service;

import com.hejianji.mall.pojo.User;
import com.hejianji.mall.vo.ResponseVo;


public interface IUserService {
    /**
     * 1.注册
     */
    ResponseVo<User> register(User user);

    /**
     * 登录
     */
    ResponseVo<User> login(String username,String password);

}
