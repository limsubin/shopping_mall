package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductSubDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.ProductReadResult;
import com.lsb.portfolio.shopping_mall.interfaces.IProductIndex;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class ProductReadVo implements IResult<ProductReadResult>, IProductIndex {
    private final int productIndex;

    private ProductReadResult result;
    //private ReviewResult reviewResult;
    private ProductDto productDto;
    private ProductSubDto productSubDto;
    private UserDto userDto;

    public ProductReadVo(int productIndex) {
        this.productIndex = productIndex;
    }

    @Override
    public int getProductIndex() {
        return productIndex;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

/*    public ReviewResult getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(ReviewResult reviewResult) {
        this.reviewResult = reviewResult;
    }*/

    public ProductSubDto getProductSubDto() {
        return productSubDto;
    }

    public void setProductSubDto(ProductSubDto productSubDto) {
        this.productSubDto = productSubDto;
    }

    @Override
    public void setResult(ProductReadResult productReadResult) {
        this.result = productReadResult;
    }

    @Override
    public ProductReadResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
