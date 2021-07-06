package com.lsb.portfolio.shopping_mall.dtos.cart;

public class CartDto {
    private final String productName;
    private final int productPrice;
    private final String imageBase64;
    private final String size;
    private final int sizeIndex;
    private final String color;
    private final int colorIndex;
    private final int cartIndex;
    private final int cartCount;
    private final int cartSubtotalPrice;

    public CartDto(String productName, int productPrice, String imageBase64, String size, int sizeIndex, String color, int colorIndex, int cartIndex, int cartCount, int cartSubtotalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageBase64 = imageBase64;
        this.size = size;
        this.sizeIndex = sizeIndex;
        this.color = color;
        this.colorIndex = colorIndex;
        this.cartIndex = cartIndex;
        this.cartCount = cartCount;
        this.cartSubtotalPrice = cartSubtotalPrice;
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

    public int getCartCount() {
        return cartCount;
    }

    public int getCartSubtotalPrice() {
        return cartSubtotalPrice;
    }

    public int getSizeIndex() {
        return sizeIndex;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public int getCartIndex() {
        return cartIndex;
    }
}
