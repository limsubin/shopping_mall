package com.lsb.portfolio.shopping_mall.models.shop.manager;

import com.lsb.portfolio.shopping_mall.dtos.shop.*;
import com.lsb.portfolio.shopping_mall.dtos.category.SubCategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.image.ByteArrayDto;
import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface IManagerModel {
    List<CategoryDto> selectCategories();

    List<SubCategoryDto> selectSubCategories();

    int selectCategoryCount(@Param("code") String code);

    void insertProduct(
            @Param("memberIndex") int memberIndex,
            @Param("productName") String productName,
            @Param("productCategory") String productCategory,
            @Param("productPrice") int productPrice,
            @Param("thumbnail") byte[] thumbnail,
            @Param("productContent") String productContent);

    void insertSubProduct(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex,
            @Param("subCategory") String subCategory);

    ProductSubDto selectBySubProductId(
            @Param("productIndex") int index);

    int selectBySubProductCount(
            @Param("productIndex") int index);

    int selectLastInsertId();

    void insertSize(
            @Param("productIndex") int index,
            @Param("name") String name);

    void insertColor(
            @Param("productIndex") int index,
            @Param("name") String name);

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

    ByteArrayDto selectImage(@Param("key") String key);

    List<ProductOptionDetailDto> selectOptionDetails(@Param("productIndex") int productIndex);

    void updateOptionDetail(
            @Param("sizeIndex") int sizeIndex,
            @Param("colorIndex") int colorIndex,
            @Param("premium") int premium,
            @Param("stock") int stock);


    void deleteOptionDetail(
            @Param("sizeIndex") int sizeIndex,
            @Param("colorIndex") int colorIndex);

    void updateProduct(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex,
            @Param("productName") String productName,
            @Param("productCategory") String productCategory,
            @Param("productPrice") int productPrice,
            @Param("productContent") String productContent);

    void updateProductAndImage(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex,
            @Param("productName") String productName,
            @Param("productCategory") String productCategory,
            @Param("productPrice") int productPrice,
            @Param("thumbnail") byte[] thumbnail,
            @Param("productContent") String productContent);

    void updateSubProduct(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex,
            @Param("subCategory") String subCategory);

    void deleteSubProduct(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex);

    List<ProductSizeDto> selectSizes(@Param("productIndex") int productIndex);

    List<ProductColorDto> selectColors(@Param("productIndex") int productIndex);

    void deleteSize(@Param("sizeIndex") int sizeIndex);

    void deleteColor(@Param("colorIndex") int colorIndex);

    void deleteProduct(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex);

    int selectProductByMemberCount(
            @Param("memberIndex") int memberIndex);

    ArrayList<ProductDto> selectProductByMember(
            @Param("memberIndex") int memberIndex,
            @Param("offset") int offset,
            @Param("perPage") int perPage);


    ByteArrayDto selectProductImage(
            @Param("productIndex") String productIndex);
}
