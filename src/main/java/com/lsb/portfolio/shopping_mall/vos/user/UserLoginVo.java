package com.lsb.portfolio.shopping_mall.vos.user;

import com.lsb.portfolio.shopping_mall.dtos.cart.CartTotalCountDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.user.UserLoginResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class UserLoginVo implements IResult<UserLoginResult> {
    private final String email;
    private final String password;

    private UserLoginResult result;
    private UserDto userDto;
    private CartTotalCountDto cartTotalCountDto;

    public UserLoginVo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public CartTotalCountDto getCartTotalCountDto() {
        return cartTotalCountDto;
    }

    public void setCartTotalCountDto(CartTotalCountDto cartTotalCountDto) {
        this.cartTotalCountDto = cartTotalCountDto;
    }

    @Override
    public void setResult(UserLoginResult userLoginResult) {
        this.result = userLoginResult;
    }

    @Override
    public UserLoginResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
