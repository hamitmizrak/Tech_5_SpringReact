package com.hamitmizrak.tech5.business.services;

import java.util.List;

// D: Dto
// E: Entity
public interface IRolesUserServices<D,E>{

    ////////////////////////////////////////////////////////////
    // MODEL MAPPER
    public D entityToDto(E e);
    public E dtoToEntity(D d);

    ////////////////////////////////////////////////////////////
    // SPEED DATA
    public List<D> rolesServiceSpeedData(Long key);

    // ALL DELETE
    public String rolesServiceDeleteAll();

    ////////////////////////////////////////////////////////////
    // Email adresinden kullanıcı rolünü bulmak
    public D userEmailFindRoles(String emailAddress);

    ////////////////////////////////////////////////////////////
    // ROLE  C R U D
    // CREATE
    public D rolesServiceCreate(D d);

    // LIST
    public List<D> rolesServiceList();

    // FIND
    public D rolesServiceFindById(Long id);

    // UPDATE
    public D rolesServiceUpdate(Long id, D d);

    // DELETE
    public D rolesServiceDeleteById(Long id);

} //end interface
