package com.lsb.portfolio.shopping_mall.models.category;

import com.lsb.portfolio.shopping_mall.dtos.category.ByteArrayDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ICategoryModel {
    ByteArrayDto selectImage(@Param("key") String key);
}
