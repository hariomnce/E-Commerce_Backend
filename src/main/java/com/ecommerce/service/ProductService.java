package com.ecommerce.service;

<<<<<<< HEAD

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
=======
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

>>>>>>> ad25d3847547a84b5ec9297aae19d8ee3b18469b
    Product findProductById(Long id) throws ProductException;

    Page<Product> getAllProduct(String category, List<String> colors, List<String> Sizes, Integer minPrice, Integer maxPrice,
                                Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

<<<<<<< HEAD
    List<Product> searchProduct(String query);


    List<Product> findProductByCategory(String category);
=======

    List<Product> searchProduct(String query);
>>>>>>> ad25d3847547a84b5ec9297aae19d8ee3b18469b
}
