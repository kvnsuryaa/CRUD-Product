package com.example.demo4.product;

import com.example.demo4.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String code;
    @NotNull(message = "Name is empty")
    @NotEmpty(message = "Name is empty")
    private String name;
    @NotNull(message = "Price is empty")
    private Integer price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @Transient
    private Long categoryId;
    @Transient
    private String categoryDesc;
    public Product() {

    }

    public Product(Long id, String code, String name, Integer price, Long categoryId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return category.getId();
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDesc() {
        return category.getName();
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = "";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", categoryId=" + categoryId +
                ", categoryDesc='" + categoryDesc + '\'' +
                '}';
    }
}
