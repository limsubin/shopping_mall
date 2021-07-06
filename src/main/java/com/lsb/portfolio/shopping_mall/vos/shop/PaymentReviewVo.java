package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.PaymentReviewResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class PaymentReviewVo implements IResult<PaymentReviewResult> {
    private final String content;
    private final int ratingOptions;
    private final int productIndex;
    private final int orderIndex;

    private UserDto userDto;
    private PaymentReviewResult result;

    public PaymentReviewVo(String content, int ratingOptions, int productIndex, int orderIndex) {
        this.content = content;
        this.ratingOptions = ratingOptions;
        this.productIndex = productIndex;
        this.orderIndex = orderIndex;
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

    public int getOrderIndex() {
        return orderIndex;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public PaymentReviewResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }

    @Override
    public void setResult(PaymentReviewResult result) {
        this.result = result;
    }
}
