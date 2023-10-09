
package com.ecommerce.controller;

import com.ecommerce.exception.ProductException;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;
    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest request) throws ProductException {
        Product createProduct = productService.CreateProduct(request);
        return new ResponseEntity<Product>(createProduct, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {

        System.out.println("delete product controller....");
        String msg = productService.deleteProduct(productId);

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
    public ResponseEntity<List<Product>> createMultipleProduct(@RequestBody List<CreateProductRequest> requests) throws
            ProductException {
        List<Product> productList = new ArrayList<>();
        for (CreateProductRequest productRequest : requests) {
            Product product = productService.CreateProduct(productRequest);
            productList.add(product);
        }
        ApiResponse response = new ApiResponse("product created successfully", true);
        return new ResponseEntity<List<Product>>(productList, HttpStatus.CREATED);
    }

    @GetMapping("/profiles")
    public String getUserProfileHandlerNew() {
        return "tested";
    }

    @GetMapping("/profileNew")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String authorization) throws UserException {

        System.out.println("api/user/profile");
        User user = userService.findUserProfileByJwt(authorization);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);

    }

}

