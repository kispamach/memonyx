package com.codecool.memonyx.controller;

import com.codecool.memonyx.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/shops/")
public class ShopController {
    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getShop(@PathVariable Long id) {
        return shopService.findShop(id);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllShop() {
        return shopService.findAllShop();
    }

    @PostMapping("")
    public ResponseEntity<?> addShop() {
        return shopService.addShop();
    }
}
