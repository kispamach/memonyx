package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.RoleType;
import com.codecool.memonyx.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}
