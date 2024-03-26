package com.sua.product.mamagement.infrastructure;

import com.sua.product.mamagement.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    // 상품 추가
    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }

    // 상품 id로 조회
    public Product findById(Long id) {
        return products.stream().filter(product -> product.sameId(id)) //스트림 API filter 사용
                .findFirst() //filter에 걸린 product 중 첫 번째 product의 Optional 객체 반환.
                .orElseThrow(); //Optional을 product 타입으로 변환. 비었으면 NoSuchElementException. 들어있으면 product 반환.
    }

    // 전체 상품 조회
    public List<Product> findAll() {
        return products;
    }

    // 이름으로 상품 조회
    public List<Product> findByNameContaining(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    // 상품 수정
    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product); //indexOf는 매개변수와 동일한 인스턴스의 index 리턴 => equals 재정의 필요
        products.set(indexToModify, product);
        return product;
    }

    // 상품 삭제
    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }

}
