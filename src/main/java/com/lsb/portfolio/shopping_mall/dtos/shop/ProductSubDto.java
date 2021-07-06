package com.lsb.portfolio.shopping_mall.dtos.shop;

public class ProductSubDto {
    private final int productIndex;
    private final String subCategory;

    public ProductSubDto(int productIndex, String subCategory) {
        this.productIndex = productIndex;
        this.subCategory = subCategory;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public String getSubCategory() {
        return subCategory;
    }
}
