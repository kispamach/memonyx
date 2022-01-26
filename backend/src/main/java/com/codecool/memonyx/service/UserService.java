package com.codecool.memonyx.service;


import com.codecool.memonyx.entity.User;
import com.codecool.memonyx.payload.response.MessageResponse;
import com.codecool.memonyx.payload.response.UserResponse;
import com.codecool.memonyx.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public ResponseEntity<?> findUserById(Long id) {
        User user = userRepository.findUserById(id).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName()));
    }
}
