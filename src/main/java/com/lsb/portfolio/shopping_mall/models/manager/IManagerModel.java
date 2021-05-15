package com.lsb.portfolio.shopping_mall.models.manager;

import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IManagerModel {
    List<CategoryDto> selectCategories();

    int selectCategoryCount(@Param("code") String code);
    //TODO 2021-05-13 여기서부터 하기!! : 파라미터 더 늘리기
    void insertProduct(
            @Param("memberIndex") int memberIndex,
            @Param("productName") String productName,
            @Param("productCategory") String productCategory,
            @Param("productPrice") int productPrice,
            @Param("thumbnail") byte[] thumbnail,
            @Param("productContent") String productContent);

    int selectLastInsertId();

    void insertSize(
            @Param("productIndex") int index,
            @Param("name") String name);

    void insertColor(
            @Param("productIndex") int index,
            @Param("name") String name);

    //TODO 여기서부터 밑에꺼 만들면 됨
    int selectSizeCount(
            @Param("productIndex") int index,
            @Param("name") String name);

    int selectColorCount(
            @Param("productIndex") int index,
            @Param("name") String name);

    int selectLikeSizeIndex(
            @Param("productIndex") int index,
            @Param("name") String name);

    int selectLikeColorIndex(
            @Param("productIndex") int index,
            @Param("name") String name);

    void insertOptionDetail(
            @Param("sizeIndex") int sizeIndex,
            @Param("colorIndex") int colorIndex,
            @Param("premium") int premium,
            @Param("stock") int stock);

    void insertImage(
            @Param("key") String key,
            @Param("image") byte[] image);
}
