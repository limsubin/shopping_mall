package com.lsb.portfolio.shopping_mall.vos.user;

import com.lsb.portfolio.shopping_mall.nums.UserRegisterResult;

public class UserRegisterVo {
    private final String email;
    private final String nickname;
    private final String password;
    private final String name;
    private final String contact;
    private final String address;

    private UserRegisterResult userRegisterResult;
    private UserVo userVo;

    public UserRegisterVo(String email, String nickname, String password, String name, String contact, String address) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterResult getUserRegisterResult() {
        return userRegisterResult;
    }

    public void setUserRegisterResult(UserRegisterResult userRegisterResult) {
        this.userRegisterResult = userRegisterResult;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
