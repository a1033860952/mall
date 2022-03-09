package com.hejianji.mall.dao;

import com.hejianji.mall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper让这个类拥有查数据库的能力
@Mapper
public interface CategoryMapper {
    //@Select 是mybatis注解
    @Select("select * from mall_category where id =#{id}")
    Category finById(@Param("id") Integer id);

    Category queryById(Integer id);

}
