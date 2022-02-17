package com.codecool.memonyx.service;

import com.codecool.memonyx.controller.CartController;
import com.codecool.memonyx.controller.ProductController;
import com.codecool.memonyx.controller.ShopController;
import com.codecool.memonyx.entity.Cart;
import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.exception.CartNotFoundException;
import com.codecool.memonyx.payload.request.CartRequest;
import com.codecool.memonyx.payload.response.CartResponse;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.repository.CartRepository;
import com.codecool.memonyx.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {

    private CartRepository cartRepository;
    private ShoppingService shoppingService;
    private Utils utils;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    @Transactional
    public Cart addCart(CartRequest newCart) {
        Cart cart = new Cart();
        cart.setDate(LocalDateTime.now());

        // Add cart to shopping's cart list
        Shopping shopping = shoppingService.findShopping(newCart.getShoppingId());
        List<Cart> cartList = shopping.getCarts();
        cartList.add(cart);
        shopping.setCarts(cartList);

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateCart(Long id, CartRequest newCart) {
        Cart cart = findCartById(id);

        if (newCart.getShop() != null) cart.setShop(new Shop(newCart.getShop()));
        if (newCart.getProducts() != null) cart.setProducts(newCart.getProducts()
                .stream()
                .map(Product::new)
                .collect(Collectors.toList()));

        return cart;
    }

    public ResponseEntity<?> deleteCart(Long id) {
        cartRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Cart deleted successfully: " + id));
    }

    public CartResponse cartConvertToCartResponse(Cart cart) {
        return new CartResponse(cart.getId(),
                cart.getDate(),
                cart.getShop() == null
                        ? null
                        : utils.urlCreator(ShopController.class, cart.getShop().getId()),
                cart.getProducts()
                        .stream()
                        .map(product -> utils.urlCreator(ProductController.class, product.getId()))
                        .collect(Collectors.toList()),
                utils.urlCreator(CartController.class, cart.getId()));
    }
}
