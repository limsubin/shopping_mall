package com.lsb.portfolio.shopping_mall.vos.search;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.enums.shop.search.SearchResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

import java.util.ArrayList;

public class SearchVo implements IResult<SearchResult> {
    private final String keyword;
    private final int page;

    private SearchResult result;
    private ArrayList<ProductDto> productDtos;
    private int leftPage;
    private int rightPage;
    private int maxPage;

    public SearchVo(String keyword, int page) {
        this.keyword = keyword;
        this.page = page;
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

    public String getKeyword() {
        return keyword;
    }

    public int getPage() {
        return page;
    }

    public ArrayList<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(ArrayList<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    @Override
    public void setResult(SearchResult result) {
        this.result = result;
    }

    @Override
    public SearchResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
