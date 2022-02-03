package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.ProductRequest;
import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.request.UserUpdateRequest;
import com.codecool.memonyx.payload.response.ShopResponse;
import com.codecool.memonyx.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/shops/")
public class ShopController {

    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getShop(@PathVariable Long id) {
        return ResponseEntity.ok(new ShopResponse(shopService.findShop(id)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllShop() {
        return ResponseEntity.ok(shopService.findAllShop()
                .stream()
                .map(ShopResponse::new)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addShop(@RequestBody ShopRequest newShop) {
        return ResponseEntity.ok(new ShopResponse(shopService.addShop(newShop)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateShop(@RequestBody ShopRequest newShop, @PathVariable Long id){
        return ResponseEntity.ok(new ShopResponse(shopService.updateShop(id, newShop)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteShop(@PathVariable Long id) {
        return shopService.deleteShop(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}/add-product")
    public ResponseEntity<?> addProduct(@PathVariable Long id, @RequestBody ProductRequest product) {
        return null;
    }
}
