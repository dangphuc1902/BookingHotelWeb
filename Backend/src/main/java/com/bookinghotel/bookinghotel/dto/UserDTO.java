package com.bookinghotel.bookinghotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String emailUser;
    private String passWord;
    private String firstName;
    private String lastName;
    private RoleDTO roleDTO;
    public UserDTO(int id, String email, String firstName, String lastName, RoleDTO role) {
        this.id = id;
        this.emailUser = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleDTO = role;
    }

}
