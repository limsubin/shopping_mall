package com.lsb.portfolio.shopping_mall.vos.user;

public class UserVo {
    private final int index;
    private final String email;
    private final String password;
    private final String nickname;
    private final String name;
    private final String contact;
    private final String address;

    public UserVo(int index, String email, String password, String nickname, String name, String contact, String address) {
        this.index = index;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public int getIndex() {
        return index;
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
}
