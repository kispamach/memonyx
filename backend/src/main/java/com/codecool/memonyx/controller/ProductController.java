package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.ProductRequest;
import com.codecool.memonyx.payload.response.ProductResponse;
import com.codecool.memonyx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/products/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductResponse(productService.findProduct(id)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProduct()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest product) {
        System.out.println(product.getShopId());
        return ResponseEntity.ok(new ProductResponse(productService.addProduct(product)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest newProduct, @PathVariable Long id) {
        return ResponseEntity.ok(new ProductResponse(productService.updateProduct(id, newProduct)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
