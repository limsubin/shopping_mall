package com.lsb.portfolio.shopping_mall.services.category;

import com.lsb.portfolio.shopping_mall.models.category.ICategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final ICategoryModel categoryModel;

    @Autowired
    public CategoryService(ICategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }
    
    public byte[] getImage(String key){
        return this.categoryModel.selectImage(key).getValue();
    }
}
