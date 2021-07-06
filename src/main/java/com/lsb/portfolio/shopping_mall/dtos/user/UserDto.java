package com.lsb.portfolio.shopping_mall.dtos.user;

public class UserDto {
    public static final String CLASS_NAME = "user"; //"com.lsb.portfolio.shopping_mall.dtos.user.UserDto";
    public static final String CART_TOTAL_COUNT_NAME = "cartTotalCount";

    private final int index;
    private final String email;
    private final String password;
    private final String nickname;
    private final String name;
    private final String addressPost;
    private final String address;
    private final String address_detail;
    private final String birthYear;
    private final String birthMonth;
    private final String birthDay;
    private final String contactFirst;
    private final String contactSecond;
    private final String contactThird;
    private final boolean isAdmin;

    public UserDto(int index, String email, String password, String nickname, String name, String addressPost, String address, String address_detail, String birthYear, String birthMonth, String birthDay, String contactFirst, String contactSecond, String contactThird, boolean isAdmin) {
        this.index = index;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.addressPost = addressPost;
        this.address = address;
        this.address_detail = address_detail;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.contactFirst = contactFirst;
        this.contactSecond = contactSecond;
        this.contactThird = contactThird;
        this.isAdmin = isAdmin;
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

    public String getAddressPost() {
        return addressPost;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getContactFirst() {
        return contactFirst;
    }

    public String getContactSecond() {
        return contactSecond;
    }

    public String getContactThird() {
        return contactThird;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
