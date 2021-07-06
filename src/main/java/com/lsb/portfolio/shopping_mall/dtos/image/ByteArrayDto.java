package com.lsb.portfolio.shopping_mall.dtos.image;

public class ByteArrayDto {
    private final byte[] value;

    public ByteArrayDto(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }
}
