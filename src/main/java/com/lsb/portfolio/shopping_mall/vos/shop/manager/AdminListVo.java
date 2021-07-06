package com.lsb.portfolio.shopping_mall.vos.shop.manager;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.manager.AdminListResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;

public class AdminListVo implements IResult<AdminListResult> {
    private final int page;

    private UserDto userDto;
    private ArrayList<ProductDto> productDtos;
    private AdminListResult result;

    private int leftPage;
    private int rightPage;
    private int maxPage;

    public AdminListVo(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getLeftPage() {
        return leftPage;
    }

    public void setLeftPage(int leftPage) {
        this.leftPage = leftPage;
    }

    public int getRightPage() {
        return rightPage;
    }

    public void setRightPage(int rightPage) {
        this.rightPage = rightPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ArrayList<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(ArrayList<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    @Override
    public AdminListResult getResult() {
        return result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }

    @Override
    public void setResult(AdminListResult result) {
        this.result = result;
    }
}
