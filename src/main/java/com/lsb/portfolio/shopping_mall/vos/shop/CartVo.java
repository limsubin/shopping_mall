package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.cart.CartTotalCountDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.CartResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class CartVo implements IResult<CartResult> {
    private final int productIndex;
    private final String productSizeIndex;
    private final String productColorIndex;
    private final String cartCount;
    private final String cartSubtotalPrice;

    private UserDto userDto;
    private ProductReadVo productReadVo;
    private CartResult result;
    private int orderIndex;
    private CartTotalCountDto cartTotalCountDto;

    public CartVo(int productIndex, String productSizeIndex, String productColorIndex, String cartCount, String cartSubtotalPrice) {
        this.productIndex = productIndex;
        this.productSizeIndex = productSizeIndex;
        this.productColorIndex = productColorIndex;
        this.cartCount = cartCount;
        this.cartSubtotalPrice = cartSubtotalPrice;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public String getProductSizeIndex() {
        return productSizeIndex;
    }

    public String getProductColorIndex() {
        return productColorIndex;
    }

    public String getCartCount() {
        return cartCount;
    }

    public String getCartSubtotalPrice() {
        return cartSubtotalPrice;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ProductReadVo getProductReadVo() {
        return productReadVo;
    }

    public void setProductReadVo(ProductReadVo productReadVo) {
        this.productReadVo = productReadVo;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public CartTotalCountDto getCartTotalCountDto() {
        return cartTotalCountDto;
    }

    public void setCartTotalCountDto(CartTotalCountDto cartTotalCountDto) {
        this.cartTotalCountDto = cartTotalCountDto;
    }

    @Override
    public void setResult(CartResult cartResult) {
        this.result = cartResult;
    }

    @Override
    public CartResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
