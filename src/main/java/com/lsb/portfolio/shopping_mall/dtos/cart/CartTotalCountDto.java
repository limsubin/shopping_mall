package com.lsb.portfolio.shopping_mall.dtos.cart;

public class CartTotalCountDto {
    private final int cartTotalCount;

    public CartTotalCountDto(int cartTotalCount) {
        this.cartTotalCount = cartTotalCount;
    }

    public int getCartTotalCount() {
        return cartTotalCount;
    }
}
