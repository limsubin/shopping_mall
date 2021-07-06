package com.lsb.portfolio.shopping_mall.dtos.order;

public class OrderAddressDto {
    private final String orderAddressPost;
    private final String orderAddress;
    private final String orderAddressDetail;

    public OrderAddressDto(String orderAddressPost, String orderAddress, String orderAddressDetail) {
        this.orderAddressPost = orderAddressPost;
        this.orderAddress = orderAddress;
        this.orderAddressDetail = orderAddressDetail;
    }

    public String getOrderAddressPost() {
        return orderAddressPost;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getOrderAddressDetail() {
        return orderAddressDetail;
    }
}
