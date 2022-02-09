package com.codecool.memonyx.entity;

import com.codecool.memonyx.payload.request.ShoppingRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "shopping_carts",
            joinColumns = @JoinColumn(name = "shopping_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "carts_id", referencedColumnName = "id"))
    private List<Cart> carts = new ArrayList<>();

    public Shopping(ShoppingRequest shoppingRequest) {
        this.setDate(shoppingRequest.getDate());
        this.setCarts(shoppingRequest.getCarts()
                .stream()
                .map(Cart::new)
                .collect(Collectors.toList()));
    }
}
