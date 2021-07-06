package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.order.OrderAddressDto;
import com.lsb.portfolio.shopping_mall.dtos.order.OrderDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.OrderPrepareResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;

public class OrderPrepareVo implements IResult<OrderPrepareResult> {
    private final int orderIndex;
    private UserDto userDto;
    private ArrayList<OrderDto> orderDto;
    private OrderAddressDto orderAddressDto;
    private OrderPrepareResult result;

    public OrderPrepareVo(int orderIndex) {
        this.orderIndex = orderIndex;
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

    public ArrayList<OrderDto> getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(ArrayList<OrderDto> orderDto) {
        this.orderDto = orderDto;
    }

    public OrderAddressDto getOrderAddressDto() {
        return orderAddressDto;
    }

    public void setOrderAddressDto(OrderAddressDto orderAddressDto) {
        this.orderAddressDto = orderAddressDto;
    }

    @Override
    public void setResult(OrderPrepareResult orderPrepareResult) {
        this.result = orderPrepareResult;
    }

    @Override
    public OrderPrepareResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
