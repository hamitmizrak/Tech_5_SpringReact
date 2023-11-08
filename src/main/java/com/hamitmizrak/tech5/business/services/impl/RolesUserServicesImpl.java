package com.hamitmizrak.tech5.business.services.impl;

import com.hamitmizrak.tech5.bean.ModelMapperBeanClass;
import com.hamitmizrak.tech5.business.dto.RegisterDto;
import com.hamitmizrak.tech5.business.dto.RoleDto;
import com.hamitmizrak.tech5.business.services.IRolesUserServices;
import com.hamitmizrak.tech5.data.entity.RegisterEntity;
import com.hamitmizrak.tech5.data.entity.RoleEntity;
import com.hamitmizrak.tech5.data.repository.IRoleRepository;
import com.hamitmizrak.tech5.exception.HamitMizrakException;
import com.hamitmizrak.tech5.exception.Resource404NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICE
// Asıl iş yükünü yapan yer
@Service
public class RolesUserServicesImpl implements IRolesUserServices<RoleDto, RoleEntity> {

    // Injection
    private final IRoleRepository iRoleRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;


    ////////////////////////////////////////////////////////////
    // MODEL MAPPER

    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(roleEntity, RoleDto.class);
    }

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        return modelMapperBeanClass.modelMapperMethod().map(roleDto, RoleEntity.class);
    }

    ////////////////////////////////////////////////////////////
    // SPEED DATA
    @Override
    public List<RoleDto> rolesServiceSpeedData(Long key) {
        return null;
    }

    // DELETE ALL
    @Override
    public String rolesServiceDeleteAll() {
        return null;
    }

    /////////////////////////////////////////////////////////////
    // Email aramasıyla role adını bulsun
    @Override
    public RoleDto userEmailFindRoles(String emailAddress) {
        /*
        RoleEntity roleEntity=iRoleRepository.userEmailFindRoles(emailAddress);
        System.out.println(roleEntity);
        System.out.println(roleEntity.getId());
        System.out.println(roleEntity.getRoleName());*/
        //return entityToDto(roleEntity);
        return null;
    }

    /////////////////////////////////////////////////////////////
    // ROLES
    // C R U D

    // CREATE
    // Transaction: Create, Delete, Update
    @Override
    @Transactional
    public RoleDto rolesServiceCreate(RoleDto roleDto) {
        if (roleDto != null) {
            RoleEntity roleEntity = dtoToEntity(roleDto);
            roleEntity.setRoleName(roleEntity.getRoleName().toUpperCase());
            iRoleRepository.save(roleEntity);
            roleDto.setId(roleEntity.getId());
            roleDto.setSystemDate(roleEntity.getSystemDate());
            return roleDto;
        }
        return null;
    }

    // LIST
    @Override
    public List<RoleDto> rolesServiceList() {
        // Entity List
        Iterable<RoleEntity> roleEntity = iRoleRepository.findAll();
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleEntity temp : roleEntity) {
            roleDtoList.add(entityToDto(temp));
        }
        roleDtoList.forEach(System.out::println);
        return roleDtoList;
    }

    // FIND BY ID
    @Override
    public RoleDto rolesServiceFindById(Long id) {
        RoleEntity roleEntity = null;
        if (id != null) {
            roleEntity= iRoleRepository.findById(id)
                    .orElseThrow(() -> new Resource404NotFoundException(id + " nolu id yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("id null olarak geldi");
        }
        return entityToDto(roleEntity);
    }

    // UPDATE
    // Transaction: Create, Delete, Update
    @Override
    @Transactional
    public RoleDto rolesServiceUpdate(Long id, RoleDto roleDto) {
        // FIND
        RoleDto rolesServiceFindById = rolesServiceFindById(id);
        RoleEntity roleEntity = null;
        if (rolesServiceFindById != null) {
            roleEntity = dtoToEntity(roleDto);
            roleEntity.setId(id);
            iRoleRepository.save(roleEntity);
        }
        return entityToDto(roleEntity);
    }

    // DELETE BY ID
    // Transaction: Create, Delete, Update
    @Override
    @Transactional
    public RoleDto rolesServiceDeleteById(Long id) {
        //FIND
        RoleDto rolesServiceFindById = rolesServiceFindById(id);
        RegisterEntity registerEntity = null;
        if (rolesServiceFindById != null)
            iRoleRepository.deleteById(id);
        return rolesServiceFindById;
    }
} //end class Services
