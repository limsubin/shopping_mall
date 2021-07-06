package com.lsb.portfolio.shopping_mall.vos.shop.manager;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.manager.ManagerProductDeleteResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class ManagerProductDeleteVo implements IResult<ManagerProductDeleteResult> {
    private final int productIndex;

    private UserDto userDto;
    private ManagerProductDeleteResult result;
    private String category;
    private String subCategory;

    public ManagerProductDeleteVo(int productIndex) {
        this.productIndex = productIndex;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public void setResult(ManagerProductDeleteResult managerProductDeleteResult) {
        this.result = managerProductDeleteResult;
    }

    @Override
    public ManagerProductDeleteResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
