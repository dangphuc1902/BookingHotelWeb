package com.bookinghotel.bookinghotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomTypesDTO {
	private String roomType;

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public String getRoomType() {
		return roomType;
	}

}
