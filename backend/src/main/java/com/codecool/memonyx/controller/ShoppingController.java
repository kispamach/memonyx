package com.codecool.memonyx.controller;

import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.payload.response.ShoppingResponse;
import com.codecool.memonyx.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/shopping/")
public class ShoppingController {

    private ShoppingService shoppingService;

    @Autowired
    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getShopping(@PathVariable Long id) {
        return shoppingService.findShopping(id);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllShoping() {
        return shoppingService.findAllShopping();
    }

    @PostMapping("")
    public ResponseEntity<?> addShopping() {
        return shoppingService.addShopping();
    }
}
