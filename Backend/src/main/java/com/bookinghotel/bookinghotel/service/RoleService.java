package com.bookinghotel.bookinghotel.service;

import com.bookinghotel.bookinghotel.dto.RoleDTO;
import com.bookinghotel.bookinghotel.entity.RolesEntity;
import com.bookinghotel.bookinghotel.exception.RoleAlreadyExistException;
import com.bookinghotel.bookinghotel.payload.request.RoleRequest;
import com.bookinghotel.bookinghotel.repository.RoleRepository;
import com.bookinghotel.bookinghotel.service.Imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleServiceImp{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRole() {
        List<RolesEntity> rolesEntities = roleRepository.findAll();
        List<RoleDTO> roleDTOS = rolesEntities
                .stream()
                .map(role -> new RoleDTO(role.getId(), role.getName()))
                .toList();
        return roleDTOS;
    }

    @Override
    public RolesEntity createNewRole(RoleRequest theRole) {
        String roleName = "ROLE_" + theRole.getName().toUpperCase();
        if(roleRepository.existsByName(theRole.getName())){
            throw new RoleAlreadyExistException(theRole.getName()+" role already exists");
        }
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setName(roleName);
        return roleRepository.save(rolesEntity);
    }

    @Override
    public boolean deleteRole(int roleId) {
        if(roleRepository.existsById(roleId)){
            roleRepository.deleteById(roleId);
            return true;
        } else return false;
    }
}
