package com.codecool.memonyx.entity;

import com.codecool.memonyx.payload.request.CartRequest;
import com.codecool.memonyx.payload.request.ShoppingRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "carts_products",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products;


    public Cart(CartRequest cartRequest) {
        this.setDate(cartRequest.getDate());
        this.setShop(new Shop(cartRequest.getShop()));
        this.setProducts(cartRequest.getProductIds()
                .stream()
                .map(Product::new)
                .collect(Collectors.toList()));
    }
}
