package com.lsb.portfolio.shopping_mall.dtos.order;

public class OrderDto {
    private final int orderIndex;
    private final int productIndex;
    private final String productName;
    private final int productPrice;
    private final String imageBase64;
    private final String size;
    private final String color;
    private final int orderCount;
    private final int orderSubtotalPrice;

    public OrderDto(int orderIndex, int productIndex, String productName, int productPrice, String imageBase64, String size, String color, int orderCount, int orderSubtotalPrice) {
        this.orderIndex = orderIndex;
        this.productIndex = productIndex;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageBase64 = imageBase64;
        this.size = size;
        this.color = color;
        this.orderCount = orderCount;
        this.orderSubtotalPrice = orderSubtotalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getImageBase64() {
        return imageBase64;
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

    public int getProductIndex() {
        return productIndex;
    }

    public int getOrderIndex() {
        return orderIndex;
    }
}
