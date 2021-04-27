package com.lsb.portfolio.shopping_mall.vos.user;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.nums.UserLoginResult;

public class UserLoginVo {
    private final String email;
    private final String password;

    private UserLoginResult userLoginResult;
    private UserDto userDto;

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

    public UserLoginResult getUserLoginResult() {
        return userLoginResult;
    }

    public void setUserLoginResult(UserLoginResult userLoginResult) {
        this.userLoginResult = userLoginResult;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
