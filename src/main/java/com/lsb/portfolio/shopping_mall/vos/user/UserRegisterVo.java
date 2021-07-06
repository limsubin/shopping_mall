package com.lsb.portfolio.shopping_mall.vos.user;

import com.lsb.portfolio.shopping_mall.enums.user.UserRegisterResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class UserRegisterVo implements IResult<UserRegisterResult> {
    private final String email;
    private final String password;
    private final String nickname;
    private final String name;
    private final String addressPost;
    private final String address;
    private final String addressDetail;
    private final String birthYear;
    private final String birthMonth;
    private final String birthDay;
    private final String contactFirst;
    private final String contactSecond;
    private final String contactThird;

    private UserRegisterResult result;
    private UserVo userVo;

    public UserRegisterVo(String email, String password, String nickname, String name, String addressPost, String address, String addressDetail, String birthYear, String birthMonth, String birthDay, String contactFirst, String contactSecond, String contactThird) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.addressPost = addressPost;
        this.address = address;
        this.addressDetail = addressDetail;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.contactFirst = contactFirst;
        this.contactSecond = contactSecond;
        this.contactThird = contactThird;
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
        return addressDetail;
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

    public String getAddressDetail() {
        return addressDetail;
    }

    @Override
    public UserRegisterResult getResult() {
        return result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }

    @Override
    public void setResult(UserRegisterResult result) {
        this.result = result;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
