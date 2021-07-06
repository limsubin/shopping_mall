package com.lsb.portfolio.shopping_mall.interfaces;

public interface IResult<T> {
    void setResult(T t);
    T getResult();
    String getResultName();
}
