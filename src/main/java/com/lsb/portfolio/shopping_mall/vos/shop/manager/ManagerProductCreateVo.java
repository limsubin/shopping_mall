package com.lsb.portfolio.shopping_mall.vos.shop.manager;

import com.lsb.portfolio.shopping_mall.enums.shop.manager.ManagerProductCreateResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;
import org.springframework.web.multipart.MultipartFile;

public class ManagerProductCreateVo implements IResult<ManagerProductCreateResult> {
    private final String productName;
    private final int productPrice;
    private final MultipartFile thumbnail;
    private final String category;
    private final String subCategory;
    private final String sizes;
    private final String colors;
    private final String optionDetail;
    private final String productContent;

    private ManagerProductCreateResult result;
    private int productIndex;

    public ManagerProductCreateVo(String productName, int productPrice, MultipartFile thumbnail, String category, String subCategory, String sizes, String colors, String optionDetail, String productContent) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.thumbnail = thumbnail;
        this.category = category;
        this.subCategory = subCategory;
        this.sizes = sizes;
        this.colors = colors;
        this.optionDetail = optionDetail;
        this.productContent = productContent;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public MultipartFile getThumbnail() {
        return this.thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public String getSizes() {
        return sizes;
    }

    public String getColors() {
        return colors;
    }

    public String getOptionDetail() {
        return optionDetail;
    }

    public String getProductContent() {
        return productContent;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    @Override
    public ManagerProductCreateResult getResult() {
        return result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }

    @Override
    public void setResult(ManagerProductCreateResult result) {
        this.result = result;
    }
}
