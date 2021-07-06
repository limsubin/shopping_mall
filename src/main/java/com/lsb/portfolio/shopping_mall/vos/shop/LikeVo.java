package com.lsb.portfolio.shopping_mall.vos.shop;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.like.LikeResult;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class LikeVo implements IResult<LikeResult> {
    private final int productIndex;
    private final boolean isLike;

    private UserDto userDto;
    private LikeResult result;

    public LikeVo(int productIndex, boolean isLike) {
        this.productIndex = productIndex;
        this.isLike = isLike;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public boolean isLike() {
        return isLike;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public void setResult(LikeResult likeResult) {
        this.result = likeResult;
    }

    @Override
    public LikeResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
