package com.bookinghotel.bookinghotel.service.Imp;

import com.bookinghotel.bookinghotel.dto.RoleDTO;
import com.bookinghotel.bookinghotel.entity.RolesEntity;
import com.bookinghotel.bookinghotel.payload.request.RoleRequest;

import java.util.List;

public interface RoleServiceImp {
    List<RoleDTO> getAllRole();

    RolesEntity createNewRole(RoleRequest theRole);

    boolean deleteRole(int roleId);
}
