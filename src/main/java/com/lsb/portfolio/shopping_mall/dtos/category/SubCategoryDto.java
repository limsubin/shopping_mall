package com.lsb.portfolio.shopping_mall.dtos.category;

public class SubCategoryDto {
    private final int index;
    private final String categoryCode;
    private final String code;
    private final String name;

    public SubCategoryDto(int index, String categoryCode, String code, String name) {
        this.index = index;
        this.categoryCode = categoryCode;
        this.code = code;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
