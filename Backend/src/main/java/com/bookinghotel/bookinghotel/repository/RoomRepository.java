package com.bookinghotel.bookinghotel.repository;

import com.bookinghotel.bookinghotel.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
    @Query(" SELECT room FROM room room " +
            " WHERE room.roomType LIKE %:roomType% " +
            " AND room.id NOT IN (" +
            "  SELECT bookingroom.room.id FROM bookings bookingroom " +
            "  WHERE ((bookingroom.check_in <= :checkOutDate) AND (bookingroom.check_out >= :checkInDate))" +
            ")")
    List<RoomEntity> findAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
}
