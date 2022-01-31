package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.request.ShoppingRequest;
import com.codecool.memonyx.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getShopping(@PathVariable Long id) {
        return shoppingService.findShopping(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllShopping() {
        return shoppingService.findAllShopping();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addShopping() {
        return shoppingService.addShopping();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateShopping(@RequestBody ShoppingRequest newShopping, @PathVariable Long id){
        return shoppingService.updateShopping(id, newShopping);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteShopping(@PathVariable Long id) {
        shoppingService.deleteShopping(id);
    }
}
