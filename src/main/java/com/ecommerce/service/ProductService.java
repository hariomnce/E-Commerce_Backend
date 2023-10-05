package com.ecommerce.service;



import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {


    //only for Admin
    Product CreateProduct(CreateProductRequest request) throws ProductException;


    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product product) throws ProductException;

    List<Product> getAllProducts();


    //for user and admin both



    Product findProductById(Long id) throws ProductException;

    Page<Product> getAllProduct(String category, List<String> colors, List<String> Sizes, Integer minPrice, Integer maxPrice,
                                Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);


    List<Product> findProductByCategory(String category);


    List<Product> searchProduct(String query);

}
