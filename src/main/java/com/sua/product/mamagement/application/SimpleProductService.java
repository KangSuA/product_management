package com.sua.product.mamagement.application;

import com.sua.product.mamagement.domain.Product;
import com.sua.product.mamagement.infrastructure.ListProductRepository;
import com.sua.product.mamagement.presentation.ProductController;
import com.sua.product.mamagement.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;
    private ModelMapper modelMapper;

    @Autowired
    SimpleProductService(ListProductRepository listProductRepository, ModelMapper modelMapper) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDto add(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class); // 1. ProductDTO를 PRODUCT로 변환
        Product savedProduct = listProductRepository.add(product); //2. 레퍼지토리 호출
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class); //3.Product를 ProductDto로 변환
        return savedProductDto; //4. DTO 반환
    }

    public ProductDto findById(Long id) {
        Product product = listProductRepository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = listProductRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updateProduct = listProductRepository.update(product);
        ProductDto updatedProductDto = modelMapper.map(updateProduct, ProductDto.class);
        return updatedProductDto;
    }

    public void delete(Long id) {
        listProductRepository.delete(id);
    }
}
