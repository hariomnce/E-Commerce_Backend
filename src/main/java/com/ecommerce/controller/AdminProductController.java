package com.ecommerce.controller;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/admin/product")
public class AdminProductController {


    @Autowired
    ProductService productService;


    @PostMapping("/save")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest request) throws ProductException {

        Product createProduct = productService.CreateProduct(request);
        return new ResponseEntity<Product>(createProduct, HttpStatus.ACCEPTED);


    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {

        System.out.println("delete product controller....");
        String msg =    productService.deleteProduct(productId);

        System.out.println("delete product controller ...msg" + msg);
        ApiResponse response = new ApiResponse(msg, true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.ACCEPTED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProductHandler(@RequestBody Product product, @PathVariable Long productId) throws
            ProductException {

        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.ACCEPTED);
    }


    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest [] requests) throws
            ProductException{


        for (CreateProductRequest productRequest:requests){
            productService.CreateProduct(productRequest);

        }
        ApiResponse response = new ApiResponse("product created successfully",true);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
    }

}
