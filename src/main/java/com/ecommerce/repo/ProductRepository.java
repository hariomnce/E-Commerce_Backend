package com.ecommerce.repo;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR p.category.name = :category) " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountPresent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC"
    )
    public List<Product> filterProduct(@Param("category") String category,
                                       @Param("minPrice") Integer minPrice,
                                       @Param("maxPrice") Integer maxPrice,
                                       @Param("minDiscount") Integer minDiscount,
                                       @Param("sort") String sort
    );



    @Query("SELECT p FROM Product p WHERE LOWER(p.title) LIKE %:query% OR LOWER(p.description) LIKE %:query% OR LOWER(p.brand) LIKE " +
            "%:query% OR LOWER(p.category.name) LIKE %:query%")
    public List<Product> searchProduct(@Param("query") String query);



}
