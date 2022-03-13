package com.hejianji.mall.service;

import com.hejianji.mall.pojo.Category;
import com.hejianji.mall.vo.CategoryVo;
import com.hejianji.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ICategoryService {

    /**
     * 查找所有类目正常的分类
     * @return
     */
    ResponseVo<List<CategoryVo>> selectAll();

    void findSubCategoryId(Integer id, Set<Integer> resultSet);

}
