package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.ReviewResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class ReviewVo implements IResult<ReviewResult> {
    private final String content;
    private final int ratingOptions;

    private int productIndex;
    private UserDto userDto;
    private ReviewResult result;

    public ReviewVo(String content, int ratingOptions) {
        this.content = content;
        this.ratingOptions = ratingOptions;
    }

    public String getContent() {
        return content;
    }

    public int getRatingOptions() {
        return ratingOptions;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public ReviewResult getResult() {
        return result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }

    @Override
    public void setResult(ReviewResult result) {
        this.result = result;
    }
}
