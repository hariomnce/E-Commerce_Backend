package com.ecommerce.service;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    Product CreateProduct(CreateProductRequest request);

    String deleteProduct(Long productId, Product product1) throws ProductException;

    Product updateProduct(Long productId, Product product) throws ProductException;

    Product findProductById(Long id) throws ProductException;

    Page<Product> getAllProduct(String category, List<String> colors, List<String> Sizes, Integer minPrice, Integer maxPrice,
                                Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);


    List<Product> searchProduct(String query);
}
