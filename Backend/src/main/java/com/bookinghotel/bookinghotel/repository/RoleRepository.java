package com.bookinghotel.bookinghotel.repository;

import com.bookinghotel.bookinghotel.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity,Integer> {
    Optional<RolesEntity> findByName(String role);

    boolean existsByName(String role);

    boolean existsById(int id);
}
