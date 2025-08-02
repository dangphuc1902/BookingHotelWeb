package com.bookinghotel.bookinghotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@Data
@Entity(name = "bookings")
public class BookingEntity {
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCheck_in() {
		return check_in;
	}

	public void setCheck_in(LocalDate check_in) {
		this.check_in = check_in;
	}

	public LocalDate getCheck_out() {
		return check_out;
	}

	public void setCheck_out(LocalDate check_out) {
		this.check_out = check_out;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getTotalGuest() {
		return totalGuest;
	}

	public void setTotalGuest(int totalGuest) {
		this.totalGuest = totalGuest;
	}

	public String getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestFullName() {
		return guestFullName;
	}

	public void setGuestFullName(String guestFullName) {
		this.guestFullName = guestFullName;
	}

	public RoomEntity getRoom() {
		return room;
	}

	public void setRoom(RoomEntity room) {
		this.room = room;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "check_in")
    private LocalDate check_in;

    @Column(name = "check_out")
    private LocalDate check_out;

    @Column(name = "adults")
    private int adults;

    @Column(name = "children")
    private int children;

    @Column(name = "total_guest")
    private int totalGuest;

    @Column(name = "confirmation_code")
    private String confirmCode;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "guest_full_name")
    private String guestFullName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    public BookingEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingEntity(LocalDate check_in, LocalDate check_out, int adults, int children, int totalGuest, String confirmCode,
			String guestEmail, String guestFullName, RoomEntity room) {
		super();
		this.check_in = check_in;
		this.check_out = check_out;
		this.adults = adults;
		this.children = children;
		this.totalGuest = totalGuest;
		this.confirmCode = confirmCode;
		this.guestEmail = guestEmail;
		this.guestFullName = guestFullName;
		this.room = room;
	}

}
