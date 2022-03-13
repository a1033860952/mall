package com.hejianji.mall.service.impl;

import com.hejianji.mall.MallApplicationTests;
import com.hejianji.mall.enums.ResponceEnum;
import com.hejianji.mall.enums.RoleEnum;
import com.hejianji.mall.pojo.User;
import com.hejianji.mall.service.IUserService;
import com.hejianji.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional   //这个本来是用于事务的，但是在test中就起到了回滚的作用
public class UserServiceImplTest extends MallApplicationTests {

    public static final String USERNAME = "jack";

    public static final String PASSWORD = "123456";

    @Autowired
    private IUserService userService;

    @Before
    public void register() {
        User user = new User(USERNAME, PASSWORD, "jack@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
        register();
        ResponseVo<User> responseVo = userService.login(USERNAME, PASSWORD);
        Assert.assertEquals(ResponceEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}