package com.hejianji.mall;

import com.hejianji.mall.dao.CategoryMapper;
import com.hejianji.mall.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 需要这两个注解 ，test才能正常启动，如果没有，则自动装配的categoryMapper会是null的
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests  {
//    @Autowired
//    private CategoryMapper categoryMapper;

    @Test
    public void load() {
//        Category byId = categoryMapper.queryById(100001);
//        System.out.println(byId.toString());
    }

}
