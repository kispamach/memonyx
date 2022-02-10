package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.Cart;
import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.exception.ShopNotFoundException;
import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ShopService {

    private ShopRepository shopRepository;
    private CartService cartService;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public Shop findShop(Long id) {
        return shopRepository.findShopById(id).orElseThrow(() -> new ShopNotFoundException(id));
    }

    public List<Shop> findAllShop() {
        return shopRepository.findAll();
    }

    @Transactional
    public Shop addShop(ShopRequest newShop) {
        Shop shop = new Shop();
        shop.setName(newShop.getName());

        // Add shop to cart
        Cart cart = cartService.findCartById(newShop.getCartId());
        cart.setShop(shop);
        return shopRepository.save(shop);
    }

    @Transactional
    public Shop updateShop(Long id, ShopRequest newShop) {
        Shop shop = shopRepository.findShopById(id).orElseThrow(() -> new ShopNotFoundException(id));
        if (newShop.getName() != null) shop.setName(newShop.getName());
        return shop;
    }

    public ResponseEntity<?> deleteShop(Long id) {
        shopRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Shop deleted successfully: " + id));
    }
}
