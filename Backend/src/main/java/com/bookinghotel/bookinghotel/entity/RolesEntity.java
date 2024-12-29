package com.bookinghotel.bookinghotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@Entity(name = "roles")
@Setter
@Getter
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public RolesEntity(String name, List<UserEntity> users) {
		super();
		this.name = name;
		this.users = users;
	}

	public RolesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
    
}
