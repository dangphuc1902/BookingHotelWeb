package com.bookinghotel.bookinghotel.service.Imp;

import com.bookinghotel.bookinghotel.dto.RoomDTO;
import com.bookinghotel.bookinghotel.dto.RoomTypesDTO;
import com.bookinghotel.bookinghotel.payload.request.RoomRequest;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface RoomServiceImp {
    boolean addRoom(String roomType, int roomPrice, MultipartFile image);
    List<RoomDTO>  getAllRoom();
    List<RoomTypesDTO> getRoomType();
    boolean deleteRoom(int id);
    boolean updateRoom(RoomRequest roomRequest);
    RoomDTO getRoomById(int id);
    List<RoomDTO> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
}
