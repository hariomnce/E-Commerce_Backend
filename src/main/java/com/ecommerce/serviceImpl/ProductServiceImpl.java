package com.ecommerce.serviceImpl;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repo.CategoryRepository;
import com.ecommerce.repo.ProductRepository;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product CreateProduct(CreateProductRequest request) {
        Category topLevel = categoryRepository.findByName(request.getTopLevelCategory());
        if (topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(request.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepository.saveAndFlush(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(request.getSecondLevelCategory(), topLevel.getName());
        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(request.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);
            secondLevel = categoryRepository.saveAndFlush(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(request.getThirdLevelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(request.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);
            thirdLevel = categoryRepository.saveAndFlush(thirdLevelCategory);
        }

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setBrand(request.getBrand());
        product.setColour(request.getColour());
        product.setDiscountedPrice(request.getDiscountedPrice());
        product.setImageUrl(request.getImageUrl());
        product.setDiscountPresent(request.getDiscountPresent());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
        product.setSizes(request.getSizes());
        product.setCategory(thirdLevel);
        product.setCreateAt(LocalDateTime.now());

        Product savedProduct = productRepository.saveAndFlush(product);
        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId, Product product1) throws ProductException {

        Product product = findProductById(productId);
        product.getSizes().clear();

        productRepository.delete(product);

        return "product deleted successfully";
    }


    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {

        Product product1 = findProductById(productId);

        if (product.getQuantity() != 0) {

            product1.setQuantity(product.getQuantity());
        }
        return productRepository.saveAndFlush(product);
    }


    @Override
    public Product findProductById(Long id) throws ProductException {

        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ProductException("product not found with id-" + id);


    }


    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> Sizes, Integer minPrice, Integer maxPrice,
                                       Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProduct(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColour())))
                    .collect(Collectors.toList());

        }
        if (stock != null) {

            if (stock.equals("in_stock")) {
                products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());

            } else if (stock.equals("out of stock")) {

                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());

            }
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProduct = new PageImpl<>(pageContent, pageable, products.size());
        return filteredProduct;


    }


    @Override
    public List<Product> searchProduct(String query) {

        List<Product> products=productRepository.searchProduct(query);
        return products;
    }


}
