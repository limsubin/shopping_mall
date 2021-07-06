package com.lsb.portfolio.shopping_mall.dtos.shop;

public class ProductSizeDto {
    private final int index;
    private final String name;

    public ProductSizeDto(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
