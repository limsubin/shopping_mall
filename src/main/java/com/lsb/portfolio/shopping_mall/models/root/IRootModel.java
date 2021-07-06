package com.lsb.portfolio.shopping_mall.models.root;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface IRootModel {
    int selectAllProductCount();

    ArrayList<ProductDto> selectAllProduct(
            @Param("offset") int offset,
            @Param("perPage") int perPage);

    String selectMemberNickname(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex);
}
