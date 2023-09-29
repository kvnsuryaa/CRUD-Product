package com.example.demo4.product.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductPayload {
    @NotNull(message = "Name is empty")
    @NotEmpty(message = "Name is empty")
    private String name;
    private String code;
    @NotNull(message = "Price is empty")
    private int price;
    @NotNull(message = "Category ID is empty")
    private Long category_id;

    public ProductPayload() {
    }

    public ProductPayload(String name, String code, int price, Long category_id) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
