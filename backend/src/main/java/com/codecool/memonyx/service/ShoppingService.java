package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.payload.response.ShoppingResponse;
import com.codecool.memonyx.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
public class ShoppingService {

    private ShoppingRepository shoppingRepository;

    @Autowired
    public void setShoppingRepository(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    public ResponseEntity<?> findShopping(Long id) {
        Shopping shopping = shoppingRepository.findShoppingById(id).orElseThrow(() -> new ShoppingNotFoundException(id));
        return ResponseEntity.ok(new ShoppingResponse(shopping));
    }

    public ResponseEntity<?> findAllShopping() {
        return ResponseEntity.ok(shoppingRepository.findAll()
                .stream()
                .map(ShoppingResponse::new)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> addShopping() {
        Shopping shopping = new Shopping();
        shopping.setDate(LocalDateTime.now());
        return ResponseEntity.ok(new ShoppingResponse(shoppingRepository.save(shopping)));
    }
}
