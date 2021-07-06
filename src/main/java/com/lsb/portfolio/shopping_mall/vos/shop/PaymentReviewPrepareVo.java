package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.order.OrderDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.PaymentReviewPrepareResult;
import com.lsb.portfolio.shopping_mall.enums.shop.PaymentReviewResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class PaymentReviewPrepareVo implements IResult<PaymentReviewPrepareResult> {
    private final int productIndex;
    private final int orderIndex;

    private PaymentReviewPrepareResult result;
    private OrderDto orderDto;
    private UserDto userDto;

    public PaymentReviewPrepareVo(int productIndex, int orderIndex) {
        this.productIndex = productIndex;
        this.orderIndex = orderIndex;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    @Override
    public PaymentReviewPrepareResult getResult() {
        return result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }

    @Override
    public void setResult(PaymentReviewPrepareResult result) {
        this.result = result;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
