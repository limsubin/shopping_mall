package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.PaymentResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class PaymentVo implements IResult<PaymentResult> {
    private final String addressPost;
    private final String address;
    private final String addressDetail;
    private final String paymentMethod;
    private final String orderCount;
    private final String productIndex;

    private PaymentResult result;
    private int orderIndex;
    private UserDto userDto;

    public PaymentVo(String addressPost, String address, String addressDetail, String paymentMethod, String orderCount, String productIndex) {
        this.addressPost = addressPost;
        this.address = address;
        this.addressDetail = addressDetail;
        this.paymentMethod = paymentMethod;
        this.orderCount = orderCount;
        this.productIndex = productIndex;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public String getProductIndex() {
        return productIndex;
    }

    @Override
    public void setResult(PaymentResult paymentResult) {
        this.result = paymentResult;
    }

    @Override
    public PaymentResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
