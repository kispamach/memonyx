package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.request.UserUpdateRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.ShopResponse;
import com.codecool.memonyx.payload.response.ShoppingResponse;
import com.codecool.memonyx.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ShopService {

    private ShopRepository shopRepository;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ResponseEntity<?> findShop(Long id) {
        Shop shop = shopRepository.findShopById(id).orElseThrow(() -> new ShopNotFoundException(id));
        return ResponseEntity.ok(new ShopResponse(shop));
    }

    public ResponseEntity<?> findAllShop() {
        return ResponseEntity.ok(shopRepository.findAll()
                .stream()
                .map(ShopResponse::new)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> addShop(ShopRequest newShop) {
        Shop shop = new Shop();
        shop.setName(newShop.getName());
        return ResponseEntity.ok(new ShopResponse(shopRepository.save(shop)));
    }

    @Transactional
    public ResponseEntity<?> updateShop(Long id, ShopRequest newShop) {
        Shop shop = shopRepository.findShopById(id).orElseThrow(() -> new ShopNotFoundException(id));
        if (newShop.getName() != null) shop.setName(newShop.getName());
        shop.setProducts(newShop.getProducts());
        return ResponseEntity.ok(new ShopResponse(shop));
    }

    public ResponseEntity<?> deleteShop(Long id) {
        shopRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Shop deleted successfully: " + id));
    }
}
