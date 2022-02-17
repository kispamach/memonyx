package com.codecool.memonyx.service;

import com.codecool.memonyx.controller.ShopController;
import com.codecool.memonyx.entity.Cart;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.exception.ShopNotFoundException;
import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.ShopResponse;
import com.codecool.memonyx.repository.ShopRepository;
import com.codecool.memonyx.util.Utils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ShopService {

    private ShopRepository shopRepository;
    private CartService cartService;
    private Utils utils;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public Shop findShop(Long id) {
        return shopRepository.findShopById(id).orElseThrow(() -> new ShopNotFoundException(id));
    }

    public List<Shop> findAllShop() {
        return shopRepository.findAll();
    }

    @Transactional
    public Shop addShop(ShopRequest newShop) {
        Cart cart = cartService.findCartById(newShop.getCartId());
        //Checks the existence of the shop
        if (shopRepository.existsByNameIgnoreCase(newShop.getName())) {
            Shop shop = shopRepository.findShopByNameIgnoreCase(newShop.getName()).orElse(null);
            cart.setShop(shop);
            return shop;
        }

        Shop shop = new Shop();
        shop.setName(newShop.getName());

        // Add shop to cart
        cart.setShop(shop);
        return shopRepository.save(shop);
    }

    @Transactional
    public Shop updateShop(Long id, ShopRequest newShop) {
        Shop shop = findShop(id);
        if (newShop.getName() != null) shop.setName(newShop.getName());
        return shop;
    }

    public ResponseEntity<?> deleteShop(Long id) {
        shopRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Shop deleted successfully: " + id));
    }

    public ShopResponse shopConvertToShopResponse(Shop shop) {
        return new ShopResponse(
                shop.getId(),
                shop.getName(),
                utils.urlCreator(ShopController.class, shop.getId())
        );
    }
}
