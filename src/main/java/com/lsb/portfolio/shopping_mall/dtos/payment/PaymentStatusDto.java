package com.lsb.portfolio.shopping_mall.dtos.payment;

import java.sql.Timestamp;

public class PaymentStatusDto {
    private final int orderIndex;
    private final String orderAddressPost;
    private final String orderAddress;
    private final String orderAddressDetail;
    private final int productIndex;
    private final String productName;
    private final String imageBase64;
    private final String size;
    private final String color;
    private final int orderCount;
    private final int orderSubtotalPrice;
    private final Boolean paymentState;
    private final Timestamp orderDate;

    public PaymentStatusDto(int orderIndex, String orderAddressPost, String orderAddress, String orderAddressDetail, int productIndex, String productName, String imageBase64, String size, String color, int orderCount, int orderSubtotalPrice, Boolean paymentState, Timestamp orderDate) {
        this.orderIndex = orderIndex;
        this.orderAddressPost = orderAddressPost;
        this.orderAddress = orderAddress;
        this.orderAddressDetail = orderAddressDetail;
        this.productIndex = productIndex;
        this.productName = productName;
        this.imageBase64 = imageBase64;
        this.size = size;
        this.color = color;
        this.orderCount = orderCount;
        this.orderSubtotalPrice = orderSubtotalPrice;
        this.paymentState = paymentState;
        this.orderDate = orderDate;
    }

    public int getOrderIndex() {
        return orderIndex;
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

    public int getProductIndex() {
        return productIndex;
    }

    public String getProductName() {
        return productName;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getOrderSubtotalPrice() {
        return orderSubtotalPrice;
    }

    public Boolean getPaymentState() {
        return paymentState;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
