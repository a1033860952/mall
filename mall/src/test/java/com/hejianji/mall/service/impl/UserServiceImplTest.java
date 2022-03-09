package com.hejianji.mall.service.impl;

import com.hejianji.mall.MallApplicationTests;
import com.hejianji.mall.dao.UserMapper;
import com.hejianji.mall.enums.RoleEnum;
import com.hejianji.mall.pojo.User;
import com.hejianji.mall.service.IUserSevice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional   //这个本来是用于事务的，但是在test中就起到了回滚的作用
public class UserServiceImplTest extends MallApplicationTests {
    @Autowired
    private IUserSevice iUserSevice;

    @Test
    public void register() {
        User user=new User("namete2st0","passw2ordtest","emain@2test", RoleEnum.CUSTOMER.getCode());
        iUserSevice.register(user);

    }
}