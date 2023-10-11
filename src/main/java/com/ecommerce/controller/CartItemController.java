package com.ecommerce.controller;

import com.ecommerce.exception.CartItemException;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.CartItemService;
import com.ecommerce.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@CrossOrigin
@RequestMapping("/api/cart_items")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

//    public ResponseEntity<ApiResponse>deleteCartItemHandler(@PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt)
//   throws CartItemException, UserException {
//
//
//
//    }


}
