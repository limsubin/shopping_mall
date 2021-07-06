package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.payment.PaymentStatusDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.PaymentStatusResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;

public class PaymentStatusVo implements IResult<PaymentStatusResult> {
    private final int page;

    private UserDto userDto;
    private ArrayList<PaymentStatusDto> paymentStatusDtos;
    private ArrayList<Integer> reviewStatusArray;
    private PaymentStatusResult result;

    private int leftPage;
    private int rightPage;
    private int maxPage;

    public PaymentStatusVo(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ArrayList<PaymentStatusDto> getPaymentStatusDtos() {
        return paymentStatusDtos;
    }

    public void setPaymentStatusDtos(ArrayList<PaymentStatusDto> paymentStatusDtos) {
        this.paymentStatusDtos = paymentStatusDtos;
    }

    public int getLeftPage() {
        return leftPage;
    }

    public void setLeftPage(int leftPage) {
        this.leftPage = leftPage;
    }

    public int getRightPage() {
        return rightPage;
    }

    public void setRightPage(int rightPage) {
        this.rightPage = rightPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public ArrayList<Integer> getReviewStatusArray() {
        return reviewStatusArray;
    }

    public void setReviewStatusArray(ArrayList<Integer> reviewStatusArray) {
        this.reviewStatusArray = reviewStatusArray;
    }

    @Override
    public void setResult(PaymentStatusResult paymentStatusResult) {
        this.result = paymentStatusResult;
    }

    @Override
    public PaymentStatusResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
