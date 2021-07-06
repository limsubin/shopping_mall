package com.lsb.portfolio.shopping_mall.vos.shop.manager;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductSubDto;
import com.lsb.portfolio.shopping_mall.enums.shop.manager.ManagerProductPrepareEditResult;
import com.lsb.portfolio.shopping_mall.interfaces.IProductIndex;
import com.lsb.portfolio.shopping_mall.interfaces.IResult;

public class ManagerProductPrepareEditVo implements IResult<ManagerProductPrepareEditResult>, IProductIndex {
    private final int productIndex;

    private ManagerProductPrepareEditResult result;
    private ProductDto productDto;
    private ProductSubDto productSubDto;

    public ManagerProductPrepareEditVo(int productIndex) {
        this.productIndex = productIndex;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public ProductSubDto getProductSubDto() {
        return productSubDto;
    }

    public void setProductSubDto(ProductSubDto productSubDto) {
        this.productSubDto = productSubDto;
    }

    @Override
    public int getProductIndex() {
        return productIndex;
    }

    @Override
    public void setResult(ManagerProductPrepareEditResult managerProductPrepareEditResult) {
        this.result = managerProductPrepareEditResult;
    }

    @Override
    public ManagerProductPrepareEditResult getResult() {
        return this.result;
    }

    @Override
    public String getResultName() {
        return this.result.name();
    }
}
