package com.codecool.memonyx.repository;

import com.codecool.memonyx.entity.ERole;
import com.codecool.memonyx.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository {

    Optional<Role> findByName(ERole name);
}
