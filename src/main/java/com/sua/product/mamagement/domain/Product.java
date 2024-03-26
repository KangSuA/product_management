package com.sua.product.mamagement.domain;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
    public void setId(Long id) {
        this.id = id;
    }

    //파라미터로 받은 id값 -> product 인스턴스 id와 비교하여 같으면 true, 다르면 false 반환
    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public Boolean containsName(String name) {
        return this.name.contains(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id); //id 값만 같다면 같은 product로 인식.
    }
}
