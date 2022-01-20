package com.codecool.memonyx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "shopping_shops",
            joinColumns = @JoinColumn(name = "Shopping_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shops_id", referencedColumnName = "id"))
    private List<Shop> shops = new ArrayList<>();

}
