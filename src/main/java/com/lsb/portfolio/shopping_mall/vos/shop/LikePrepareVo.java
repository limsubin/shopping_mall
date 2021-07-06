package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.LikePrepareResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;

public class LikePrepareVo implements IResult<LikePrepareResult> {
    private final int page;

    private UserDto userDto;
    private LikePrepareResult result;
    private ArrayList<ProductDto> productDtos;
    private int leftPage;
    private int rightPage;
    private int maxPage;

    public LikePrepareVo(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
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

    @Override
    public void setResult(LikePrepareResult likePrepareResult) {
        this.result = likePrepareResult;
    }

    @Override
    public LikePrepareResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
