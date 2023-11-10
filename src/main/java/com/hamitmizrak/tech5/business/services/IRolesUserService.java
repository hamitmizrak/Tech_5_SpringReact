package com.hamitmizrak.tech5. business.services;

import com.hamitmizrak.tech5.business.dto.RoleDto;
import com.hamitmizrak.tech5.data.entity.RoleEntity;

import java.util.List;

public interface IRolesUserService {

    // MODEL MAPPER
    public RoleEntity DtoToEntity(RoleDto roleDto);

    public RoleDto EntityToDto(RoleEntity roleEntity);

    ////////////////////////////////////////////////////////////////////////////
    // ROLE LIST
    public List<RoleDto> rolesList();

    // ROLE CREATE
    public RoleDto rolesCreate(RoleDto roleDto);

    // ROLE FIND
    public RoleDto rolesFind(Long id);

    // ROLE UPDATE
    public RoleDto rolesUpdate(Long id,RoleDto roleDto);

    // ROLE DELETE
    public RoleDto rolesDelete(Long id);

    ////////////////////////////////////////////////////////////////////////////
    //Email adresinde kullanı rolünü bulmak
    public RoleDto userEmailFindRoles(String emailAddress);

} //end Class

