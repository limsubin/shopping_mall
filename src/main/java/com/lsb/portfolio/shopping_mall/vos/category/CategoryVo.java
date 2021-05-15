package com.lsb.portfolio.shopping_mall.vos.category;

public class CategoryVo {
    private final String category;
    private final int page;

    public CategoryVo(String category, int page) {
        this.category = category;
        this.page = page;
    }

    public String getCategory() {
        return this.category;
    }

    public int getPage() {
        return this.page;
    }
}
