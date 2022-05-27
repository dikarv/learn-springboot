package com.enigma.enigmatboot.repository;

import com.enigma.enigmatboot.entity.ERole;
import com.enigma.enigmatboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
