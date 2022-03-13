package com.hejianji.mall.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    //承接子类目数据
    private List<CategoryVo> subCategories;
}

