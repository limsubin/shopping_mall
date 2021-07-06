package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.cart.CartTotalCountDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.OrderResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class OrderVo implements IResult<OrderResult> {
    private final String cartIndex;
    private final String cartCount;

    private UserDto userDto;
    private OrderResult result;
    private CartTotalCountDto cartTotalCountDto;
    private int orderIndex;

    public OrderVo(String cartIndex, String cartCount) {
        this.cartIndex = cartIndex;
        this.cartCount = cartCount;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getCartIndex() {
        return cartIndex;
    }

    public String getCartCount() {
        return cartCount;
    }

    public CartTotalCountDto getCartTotalCountDto() {
        return cartTotalCountDto;
    }

    public void setCartTotalCountDto(CartTotalCountDto cartTotalCountDto) {
        this.cartTotalCountDto = cartTotalCountDto;
    }

    @Override
    public void setResult(OrderResult orderResult) {
        this.result = orderResult;
    }

    @Override
    public OrderResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
