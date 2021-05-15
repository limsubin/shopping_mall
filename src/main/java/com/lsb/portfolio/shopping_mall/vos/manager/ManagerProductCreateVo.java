package com.lsb.portfolio.shopping_mall.vos.manager;

import com.lsb.portfolio.shopping_mall.enums.manager.ManagerProductCreateResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public class ManagerProductCreateVo {
    private final String productName;
    private final int productPrice;
    private final MultipartFile thumbnail;
    private final String category;
    private final String sizes;
    private final String colors;
    //TODO Object[]로 할지 String[]으로 할지
    private final String optionDetail;
    //private final int[] premiums;
    //private final int[] stocks;
    private final String productContent;

    private ManagerProductCreateResult result;

    public ManagerProductCreateVo(String productName, int productPrice, MultipartFile thumbnail, String category, String sizes, String colors, String optionDetail, String productContent) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.thumbnail = thumbnail;
        this.category = category;
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

    public ManagerProductCreateResult getResult() {
        return result;
    }

    public void setResult(ManagerProductCreateResult result) {
        this.result = result;
    }
}
