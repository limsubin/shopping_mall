package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.cart.CartDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.CartPrepareResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;

public class CartPrepareVo implements IResult<CartPrepareResult> {
    //private final int productIndex;

    private UserDto userDto;
    private ArrayList<CartDto> cartDto;
    private CartPrepareResult result;

    //public CartPrepareVo(int productIndex) {
    //    this.productIndex = productIndex;
    //}

    //public int getProductIndex() {
    //    return productIndex;
    //}

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ArrayList<CartDto> getCartDto() {
        return cartDto;
    }

    public void setCartDto(ArrayList<CartDto> cartDto) {
        this.cartDto = cartDto;
    }

    @Override
    public void setResult(CartPrepareResult cartPrepareResult) {
        this.result = cartPrepareResult;
    }

    @Override
    public CartPrepareResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
