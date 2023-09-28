package com.ecommerce.repo;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> filterProduct(@Param("category")String category,
                                       @Param("minPrice")Integer minPrice,
                                       @Param("maxPrice")Integer maxPrice,
                                       @Param("maxDiscount")Integer maxDiscount,
                                       @Param("minDiscount")Integer minDiscount,
                                       @Param("sort")String sort
                                       );
}
