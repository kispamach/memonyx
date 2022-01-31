package com.codecool.memonyx.service;

import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.payload.request.ProductRequest;
import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.ProductResponse;
import com.codecool.memonyx.payload.response.ShopResponse;
import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> findProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return ResponseEntity.ok(new ProductResponse(product));
    }

    public ResponseEntity<?> findAllProduct() {
        return ResponseEntity.ok(productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> addProduct(ProductRequest newProduct) {
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setMeasuringUnit(newProduct.getMeasuringUnit());
        return ResponseEntity.ok(new ProductResponse(productRepository.save(product)));
    }

    @Transactional
    public ResponseEntity<?> updateProduct(Long id, ProductRequest newProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (newProduct.getName() != null) product.setName(newProduct.getName());
        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setMeasuringUnit(newProduct.getMeasuringUnit());
        return ResponseEntity.ok(new ProductResponse(product));
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Product deleted successfully: " + id));
    }
}
