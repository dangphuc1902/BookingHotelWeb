package com.bookinghotel.bookinghotel.repository;

import com.bookinghotel.bookinghotel.entity.BookingEntity;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    Optional<BookingEntity> findByconfirmCode (String confirmCode);
    List<BookingEntity> findByGuestEmail(String email);
}
