package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.cart.CartTotalCountDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.CartDeleteResult;
import com.lsb.portfolio.shopping_mall.enums.shop.OrderResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class CartDeleteVo implements IResult<CartDeleteResult> {
    private final String cartIndexArray;

    private UserDto userDto;
    private CartDeleteResult result;
    private CartTotalCountDto cartTotalCountDto;

    public CartDeleteVo(String cartIndexArray) {
        this.cartIndexArray = cartIndexArray;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getCartIndexArray() {
        return cartIndexArray;
    }

    public CartTotalCountDto getCartTotalCountDto() {
        return cartTotalCountDto;
    }

    public void setCartTotalCountDto(CartTotalCountDto cartTotalCountDto) {
        this.cartTotalCountDto = cartTotalCountDto;
    }

    @Override
    public void setResult(CartDeleteResult cartDeleteResult) {
        this.result = cartDeleteResult;
    }

    @Override
    public CartDeleteResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
