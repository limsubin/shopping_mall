package com.lsb.portfolio.shopping_mall.vos.shop.manager;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.manager.ManagerProductEditResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;
import org.springframework.web.multipart.MultipartFile;

public class ManagerProductEditVo implements IResult<ManagerProductEditResult> {
    private final String productName;
    private final int productPrice;
    private final MultipartFile thumbnail;
    private final String category;
    private final String subCategory;
    private final String sizes;
    private final String colors;
    private final String optionDetail;
    private final String productContent;

    private ManagerProductPrepareEditVo prepareEditVo;
    private UserDto userDto;
    private ManagerProductEditResult result;
    //private MultipartFile thumbnail;

    public ManagerProductEditVo(String productName, int productPrice, MultipartFile thumbnail, String category, String subCategory, String sizes, String colors, String optionDetail, String productContent) {
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
        return thumbnail;
    }

    //public void setThumbnail(MultipartFile thumbnail) {
    //    this.thumbnail = thumbnail;
    //}

    public String getCategory() {
        return category;
    }

    public String getOptionDetail() {
        return optionDetail;
    }

    public String getProductContent() {
        return productContent;
    }

    public String getSizes() {
        return sizes;
    }

    public String getColors() {
        return colors;
    }

    public ManagerProductPrepareEditVo getPrepareEditVo() {
        return prepareEditVo;
    }

    public void setPrepareEditVo(ManagerProductPrepareEditVo prepareEditVo) {
        this.prepareEditVo = prepareEditVo;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getSubCategory() {
        return subCategory;
    }

    @Override
    public void setResult(ManagerProductEditResult managerProductEditResult) {
        this.result = managerProductEditResult;
    }

    @Override
    public ManagerProductEditResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
