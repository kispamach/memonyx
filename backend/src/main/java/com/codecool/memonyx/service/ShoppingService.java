package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.request.ShoppingRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.ShopResponse;
import com.codecool.memonyx.payload.response.ShoppingResponse;
import com.codecool.memonyx.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ResponseEntity<?> updateShopping(Long id, ShoppingRequest newShopping) {
        Shopping shopping = shoppingRepository.findShoppingById(id).orElseThrow(() -> new ShoppingNotFoundException(id));
        shopping.setShops(newShopping.getShops());
        return ResponseEntity.ok(new ShoppingResponse(shopping));
    }

    public ResponseEntity<?> deleteShopping(Long id) {
        shoppingRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Shopping deleted successfully: " + id));
    }
}
