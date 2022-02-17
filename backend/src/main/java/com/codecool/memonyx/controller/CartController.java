package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.CartRequest;
import com.codecool.memonyx.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/carts/")
public class CartController {

    private final CartService cartService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.cartConvertToCartResponse(cartService.findCartById(id)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(cartService.findAllCart()
                .stream()
                .map(cartService::cartConvertToCartResponse)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody CartRequest cart) {
        return ResponseEntity.ok(cartService.cartConvertToCartResponse(cartService.addCart(cart)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@RequestBody CartRequest newCart, @PathVariable Long id) {
        return ResponseEntity.ok(cartService.cartConvertToCartResponse(cartService.updateCart(id, newCart)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return cartService.deleteCart(id);
    }
}
