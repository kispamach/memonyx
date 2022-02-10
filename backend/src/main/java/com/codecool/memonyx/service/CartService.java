package com.codecool.memonyx.service;

import com.codecool.memonyx.entity.Cart;
import com.codecool.memonyx.entity.Product;
import com.codecool.memonyx.entity.Shop;
import com.codecool.memonyx.entity.Shopping;
import com.codecool.memonyx.exception.CartNotFoundException;
import com.codecool.memonyx.payload.request.CartRequest;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.repository.CartRepository;
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

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
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
}
