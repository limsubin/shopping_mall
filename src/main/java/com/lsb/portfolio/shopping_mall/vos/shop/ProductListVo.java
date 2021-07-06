package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.category.SubCategoryDto;
import com.lsb.portfolio.shopping_mall.enums.shop.ProductListResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;
import java.util.List;

public class ProductListVo implements IResult<ProductListResult> {
    private final String categoryCode;
    private final String subCategoryCode;
    private final int page;

    private int leftPage;
    private int rightPage;
    private int maxPage;

    private ProductListResult result;
    private ArrayList<ProductDto> lists;
    private List<CategoryDto> categoryDtos;
    private List<SubCategoryDto> subCategoryDtos;
    private List<String> nicknameArray;

    public ProductListVo(String categoryCode, String subCategoryCode, int page) {
        this.categoryCode = categoryCode;
        this.subCategoryCode = subCategoryCode;
        this.page = page;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public int getPage() {
        return this.page;
    }

    @Override
    public void setResult(ProductListResult productListResult) {
        this.result = productListResult;
    }

    @Override
    public ProductListResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }

    public ArrayList<ProductDto> getLists() {
        return lists;
    }

    public void setLists(ArrayList<ProductDto> lists) {
        this.lists = lists;
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

    public List<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }

    public List<SubCategoryDto> getSubCategoryDtos() {
        return subCategoryDtos;
    }

    public void setSubCategoryDtos(List<SubCategoryDto> subCategoryDtos) {
        this.subCategoryDtos = subCategoryDtos;
    }

    public List<String> getNicknameArray() {
        return nicknameArray;
    }

    public void setNicknameArray(List<String> nicknameArray) {
        this.nicknameArray = nicknameArray;
    }
}
