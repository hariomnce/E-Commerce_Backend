package com.ecommerce.controller;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/product")
public class UserProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/fetch")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
                                                                      @RequestParam List<String> colour,
                                                                      @RequestParam List<String> size,
                                                                      @RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice,
                                                                      @RequestParam Integer minDiscount,
                                                                      @RequestParam String sort,
                                                                      @RequestParam String stock,
                                                                      @RequestParam Integer pageNumber,
                                                                      @RequestParam Integer pageSize) {

        Page<Product> res = productService.getAllProduct(category, colour, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber,
                pageSize);
        System.out.println("complete product");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q) {
        List<Product> products = productService.searchProduct(q);
        return new ResponseEntity<List<Product>>(products, HttpStatus.ACCEPTED);
    }
}
