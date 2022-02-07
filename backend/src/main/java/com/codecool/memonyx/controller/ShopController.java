package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.ShopRequest;
import com.codecool.memonyx.payload.response.ShopResponse;
import com.codecool.memonyx.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/shops/")
public class ShopController {

    private final ShopService shopService;


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getShop(@PathVariable Long id) {
        return ResponseEntity.ok(new ShopResponse(shopService.findShop(id)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllShop() {
        return ResponseEntity.ok(shopService.findAllShop()
                .stream()
                .map(ShopResponse::new)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addShop(@RequestBody ShopRequest newShop) {
        return ResponseEntity.ok(new ShopResponse(shopService.addShop(newShop)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateShop(@RequestBody ShopRequest newShop, @PathVariable Long id){
        return ResponseEntity.ok(new ShopResponse(shopService.updateShop(id, newShop)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteShop(@PathVariable Long id) {
        return shopService.deleteShop(id);
    }
}
