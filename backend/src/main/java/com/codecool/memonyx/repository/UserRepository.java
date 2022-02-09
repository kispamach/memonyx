package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
