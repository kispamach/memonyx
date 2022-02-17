package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.ProductRequest;
import com.codecool.memonyx.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/products/")
public class ProductController {

    private final ProductService productService;


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.productConvertToProductResponse(productService.findProduct(id)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProduct()
                .stream()
                .map(productService::productConvertToProductResponse)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest product) {
        return ResponseEntity.ok(productService.productConvertToProductResponse(productService.addProduct(product)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest newProduct, @PathVariable Long id) {
        return ResponseEntity.ok(productService.productConvertToProductResponse(productService.updateProduct(id, newProduct)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
