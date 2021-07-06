package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.OrderProductDeleteResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class OrderProductDeleteVo implements IResult<OrderProductDeleteResult> {
    private final int orderIndex;
    private final int productIndex;

    private OrderProductDeleteResult result;
    private UserDto userDto;

    public OrderProductDeleteVo(int orderIndex, int productIndex) {
        this.orderIndex = orderIndex;
        this.productIndex = productIndex;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public void setResult(OrderProductDeleteResult orderProductDeleteResult) {
        this.result = orderProductDeleteResult;
    }

    @Override
    public OrderProductDeleteResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
