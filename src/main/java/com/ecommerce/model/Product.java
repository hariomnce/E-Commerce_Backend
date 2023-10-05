package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "discountedPrice")
    private int discountedPrice;

    @Column(name = "discountPresent")
    private int discountPresent;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "brand")
    private String brand;

    private String colour;


}
