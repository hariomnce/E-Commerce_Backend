package com.ecommerce.serviceImpl;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repo.CategoryRepository;
import com.ecommerce.repo.ProductRepository;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Category CreateProduct(CreateProductRequest request) {
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
        return thirdLevel;
    }

    public List<Product> getall(String category,
                                Integer minPrice,
                                Integer maxPrice,
                                Integer maxDiscount, Integer minDiscount, String sort) {
        List pro = productRepository.filterProduct(category, minPrice, maxPrice, maxDiscount, minDiscount, sort);
        return pro;
    }
}
