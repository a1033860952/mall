package com.hejianji.mall.service.impl;

import com.hejianji.mall.consts.MallConst;
import com.hejianji.mall.dao.CategoryMapper;
import com.hejianji.mall.pojo.Category;
import com.hejianji.mall.service.ICategoryService;
import com.hejianji.mall.vo.CategoryVo;
import com.hejianji.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 查找所有类目正常的分类
     *
     * @return
     */
    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
//        List<CategoryVo> categoryVoList=new ArrayList<>();

        //categories是所有status正常的分类
        List<Category> categories = categoryMapper.selectAll();
        //查出parent_id=0
//		for (Category category : categories) {
//			if (category.getParentId().equals(MallConst.ROOT_PARENT_ID)) {
//				CategoryVo categoryVo = new CategoryVo();
//				BeanUtils.copyProperties(category, categoryVo);
//                categoryVoList.add(categoryVo);
//			}
//		}

        //和上面的不同，这个形式是采用lambda + stream实现的
        //categoryVoList是从categories转换过来的，且parentid为0的根分类
        List<CategoryVo> categoryVoList = categories.stream()
                //表示取出parentId为0的
                .filter(category -> category.getParentId().equals(MallConst.ROOT_PARENT_ID))
                //进行一次转换
                .map(this::categoryToCategoryVo)
                //对根类根据sortorder字段进行降序排序
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        //查询子目录
        findSubCategory(categories, categoryVoList);

        return ResponseVo.success(categoryVoList);
    }

    /**
     * 获取指定分类id下的所有分类
     * @param id
     * @param resultSet
     */
    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        //查找获取所有分类
        List<Category> categories = categoryMapper.selectAll();
        findSubCategoryId(id, resultSet, categories);
    }

    /**
     * 这个方法存在是为了减少数据库查询次数，提高效率
     * @param id
     * @param resultSet
     * @param categories
     */
    public void findSubCategoryId(Integer id, Set<Integer> resultSet,List<Category> categories) {
        for (Category category : categories) {
            //如果父类id和方法给的id相同，说明这个分类是这个父类id下的子分类
            if (category.getParentId().equals(id)){
                //就将这个类别的id加入到resultSet中
                resultSet.add(category.getId());
                findSubCategoryId(id,resultSet,categories);
            }
        }
    }

    private void findSubCategory(List<Category> categories,List<CategoryVo> categoryVoList){
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList =new ArrayList<>();
            for (Category category : categories) {
                if(categoryVo.getId().equals(category.getParentId())){
                    CategoryVo subCategoryVo=categoryToCategoryVo(category);
                    subCategoryVoList.add(subCategoryVo);

                }
                //对子目录进行排序
                subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                categoryVo.setSubCategories(subCategoryVoList);
                findSubCategory(categories,subCategoryVoList);
            }


        }
    }




    /**
     * 将category转换为categoryVo
     * @param category
     * @return
     */
    private CategoryVo categoryToCategoryVo(Category category){
        CategoryVo categoryVo=new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
