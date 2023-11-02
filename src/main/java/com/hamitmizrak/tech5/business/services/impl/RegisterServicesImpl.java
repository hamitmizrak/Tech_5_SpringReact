package com.hamitmizrak.tech5.business.services.impl;

import com.hamitmizrak.tech5.bean.ModelMapperBeanClass;
import com.hamitmizrak.tech5.bean.PasswordEncoderBeanClass;
import com.hamitmizrak.tech5.business.dto.RegisterDto;
import com.hamitmizrak.tech5.business.services.IRegisterServices;
import com.hamitmizrak.tech5.data.entity.RegisterEntity;
import com.hamitmizrak.tech5.data.repository.IRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICE
// Asıl iş yükünü yapan yer
@Service
public class RegisterServicesImpl implements IRegisterServices<RegisterDto, RegisterEntity> {

    // Injection
    private final IRegisterRepository iRegisterRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;

    ////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public RegisterDto entityToDto(RegisterEntity registerEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(registerEntity,RegisterDto.class);
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto) {
        return modelMapperBeanClass.modelMapperMethod().map(registerDto,RegisterEntity.class);
    }

    ////////////////////////////////////////////////////////////
    // SPEED DATA
    @Override
    public List<RegisterDto> registerServiceSpeedData(Long key) {
        return null;
    }

    @Override
    public String registerServiceDeleteAll() {
        return null;
    }

    /////////////////////////////////////////////////////////////
    // LOGIN
    // FIND SURNAME
    @Override
    public RegisterDto loginServiceFindBySurname(String surname) {
        return null;
    }

    ////////////////////////////////////////////////////////////
    // REGISTER C R U D
    // CREATE
    @Override
    public RegisterDto registerServiceCreate(RegisterDto registerDto) {
        return null;
    }

    // LIST
    @Override
    public List<RegisterDto> registerServiceList() {
        return null;
    }

    // FIND BY ID
    @Override
    public RegisterDto registerServiceFindById(Long id) {
        return null;
    }

    // UPDATE
    @Override
    public RegisterDto registerServiceUpdate(Long id, RegisterDto registerDto) {
        return null;
    }

    // DELETE BY ID
    @Override
    public RegisterDto registerServiceDeleteById(Long id) {
        return null;
    }
} //end class Services
