package com.lsb.portfolio.shopping_mall.dtos.shop;

public class ProductDto {
    private final int index;
    private final int memberIndex;
    private final String productName;
    private final String productCategory;
    private final int productPrice;
    private final String imageBase64;
    private final byte[] image;
    private final int productHit;
    private final String productContent;
    private final int productView;

    public ProductDto(int index, int memberIndex, String productName, String productCategory, int productPrice, String imageBase64, byte[] image, int productHit, String productContent, int productView) {
        this.index = index;
        this.memberIndex = memberIndex;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.imageBase64 = imageBase64;
        this.image = image;
        this.productHit = productHit;
        this.productContent = productContent;
        this.productView = productView;
    }

    public int getIndex() {
        return index;
    }

    public int getMemberIndex() {
        return memberIndex;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public byte[] getImage() {
        return image;
    }

    public int getProductHit() {
        return productHit;
    }

    public String getProductContent() {
        return productContent;
    }

    public int getProductView() {
        return productView;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
