package com.lsb.portfolio.shopping_mall.dtos.category;

public class CategoryDto {
    private final int index;
    private final String code;
    private final String name;

    public CategoryDto(int index, String code, String name) {
        this.index = index;
        this.code = code;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
