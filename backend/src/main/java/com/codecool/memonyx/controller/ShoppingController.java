package com.codecool.memonyx.controller;

import com.codecool.memonyx.payload.request.ShoppingRequest;
import com.codecool.memonyx.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/shopping/")
public class ShoppingController {

    private final ShoppingService shoppingService;


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getShopping(@PathVariable Long id) {
        return ResponseEntity.ok(shoppingService.shoppingConvertToShoppingResponse(shoppingService.findShopping(id)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllShopping() {
        return ResponseEntity.ok(shoppingService.findAllShopping()
                .stream()
                .map(shoppingService::shoppingConvertToShoppingResponse)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addShopping(@RequestBody ShoppingRequest newShopping) {
        return ResponseEntity.ok(shoppingService.shoppingConvertToShoppingResponse(shoppingService.addShopping(newShopping)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateShopping(@RequestBody ShoppingRequest newShopping, @PathVariable Long id){
        return ResponseEntity.ok(shoppingService.shoppingConvertToShoppingResponse(shoppingService.updateShopping(id, newShopping)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteShopping(@PathVariable Long id) {
        shoppingService.deleteShopping(id);
    }
}
