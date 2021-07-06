package com.lsb.portfolio.shopping_mall.dtos.shop;

public class ProductOptionDetailDto {
    private final int sizeIndex;
    private final String size;
    private final int colorIndex;
    private final String color;
    private final int premium;
    private final int stock;

    public ProductOptionDetailDto(int sizeIndex, String size, int colorIndex, String color, int premium, int stock) {
        this.sizeIndex = sizeIndex;
        this.size = size;
        this.colorIndex = colorIndex;
        this.color = color;
        this.premium = premium;
        this.stock = stock;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public int getPremium() {
        return premium;
    }

    public int getStock() {
        return stock;
    }

    public int getSizeIndex() {
        return sizeIndex;
    }

    public int getColorIndex() {
        return colorIndex;
    }
}
