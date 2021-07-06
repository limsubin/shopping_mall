package com.lsb.portfolio.shopping_mall.vos.root;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.root.AllProductResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;

public class AllProductVo implements IResult<AllProductResult> {
    private final int page;

    private UserDto userDto;
    private AllProductResult result;
    private ArrayList<ProductDto> productDtos;
    private ArrayList<String> nicknameArray;
    private int leftPage;
    private int rightPage;
    private int maxPage;

    public AllProductVo(int page) {
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

    public ArrayList<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(ArrayList<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public ArrayList<String> getNicknameArray() {
        return nicknameArray;
    }

    public void setNicknameArray(ArrayList<String> nicknameArray) {
        this.nicknameArray = nicknameArray;
    }

    @Override
    public void setResult(AllProductResult allProductResult) {
        this.result = allProductResult;
    }

    @Override
    public AllProductResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
