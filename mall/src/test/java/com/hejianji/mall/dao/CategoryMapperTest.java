package com.hejianji.mall.dao;


import com.hejianji.mall.MallApplicationTests;
import com.hejianji.mall.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


public class CategoryMapperTest extends MallApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void finById() {
//        Category byId = categoryMapper.finById(100001);
//        System.out.println(byId.toString());
    }

    @Test
    public void queryById() {
//        Category byId = categoryMapper.queryById(100001);
//        System.out.println(byId.toString());
    }
}