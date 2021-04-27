package com.lsb.portfolio.shopping_mall.dtos.user;

public class UserDto {
    public static final String CLASS_NAME = "com.lsb.portfolio.shopping_mall.dtos.user.UserDto";

    private final int index;
    private final String email;
    private final String password;
    private final String nickname;
    private final String name;
    private final String contact;
    private final String address;

    public UserDto(int index, String email, String password, String nickname, String name, String contact, String address) {
        this.index = index;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public static String getClassName() {
        return CLASS_NAME;
    }

    public int getIndex() {
        return index;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
}
