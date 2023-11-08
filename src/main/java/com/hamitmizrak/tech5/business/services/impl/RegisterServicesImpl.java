package com.hamitmizrak.tech5.business.services.impl;

import com.hamitmizrak.tech5.bean.ModelMapperBeanClass;
import com.hamitmizrak.tech5.bean.PasswordEncoderBeanClass;
import com.hamitmizrak.tech5.business.dto.RegisterDto;
import com.hamitmizrak.tech5.business.services.IRegisterServices;
import com.hamitmizrak.tech5.data.entity.RegisterEntity;
import com.hamitmizrak.tech5.data.repository.IRegisterRepository;
import com.hamitmizrak.tech5.exception.HamitMizrakException;
import com.hamitmizrak.tech5.exception.Resource404NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        return modelMapperBeanClass.modelMapperMethod().map(registerEntity, RegisterDto.class);
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto) {
        return modelMapperBeanClass.modelMapperMethod().map(registerDto, RegisterEntity.class);
    }

    ////////////////////////////////////////////////////////////
    // SPEED DATA
    @Override
    public List<RegisterDto> registerServiceSpeedData(Long key) {
        List<RegisterDto> registerDtoList = new ArrayList<>();
        RegisterEntity registerEntity = null;
        if (key != null) {
            for (int i = 1; i <= key; i++) {
                registerEntity = RegisterEntity.builder()
                        .registerNickName("nickname " + i)
                        .registerName("name " + i)
                        .registerSurname("surname " + i)
                        .registerPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode("root"))
                        .registerEmail("email" + UUID.randomUUID().toString() + "@gmail.com")
                        .registerIsPassive(true)
                        .remaningNumber(5L)
                        .build();
                iRegisterRepository.save(registerEntity);
                registerDtoList.add(entityToDto(registerEntity));
            }
        }
        return registerDtoList;
    }

    @Override
    public String registerServiceDeleteAll() {
        iRegisterRepository.deleteAll();
        System.out.println(iRegisterRepository.findAll().toString());
        return iRegisterRepository.findAll().toString();
    }

    /////////////////////////////////////////////////////////////
    // LOGIN
    // FIND SURNAME
    @Override
    public RegisterDto loginServiceFindBySurname(String surname) {
        Optional<RegisterEntity> find = iRegisterRepository.findByRegisterSurname(surname);
        RegisterDto registerDto = entityToDto(find.get());
        if (registerDto != null) {
            return registerDto;
        }
        return null;
    }

    @Override
    public RegisterDto loginServiceFindByEmail(String email) {
        Optional<RegisterEntity> findEmailWithObject = iRegisterRepository.findByRegisterEmail(email);
        RegisterDto registerDto = entityToDto(findEmailWithObject.get());
        if (registerDto != null) {
            return registerDto;
        }
        return null;
    }

    ////////////////////////////////////////////////////////////
    // REGISTER C R U D
    // CREATE
    // Transaction: Create, Delete, Update
    // import org.springframework.transaction.annotation.Transactional;
    @Override
    @Transactional
    public RegisterDto registerServiceCreate(RegisterDto registerDto) {
        if (registerDto != null) {
            RegisterEntity registerEntity = dtoToEntity(registerDto);
            // PasswordEncoder (masking)
            registerEntity.setRegisterPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode(registerDto.getRegisterPassword()));
            iRegisterRepository.save(registerEntity);
            registerDto.setId(registerEntity.getId());
            registerDto.setSystemDate(registerEntity.getSystemDate());
            return registerDto;
        }
        return null;
    }

    // LIST
    @Override
    public List<RegisterDto> registerServiceList() {
        // Entity List
        Iterable<RegisterEntity> registerEntityIterable = iRegisterRepository.findAll();
        List<RegisterDto> registerDtoList = new ArrayList<>();
        for (RegisterEntity entity : registerEntityIterable) {
            registerDtoList.add(entityToDto(entity));
        }
       /*
       log.info("Listeme: "+registerDtoList);
        registerDtoList.stream().forEach((data)->{
            System.out.println(data);
        });
        registerDtoList.forEach((data)->{
            System.out.println(data);
        });
        */
        registerDtoList.forEach(System.out::println);
        return registerDtoList;
    }

    // FIND BY ID
    @Override
    public RegisterDto registerServiceFindById(Long id) {
        // 1.YOL
        /*
         Optional<RegisterEntity>  find1=   iRegisterRepository.findById(id);
        if(find1.isPresent()){
            return entityToDto(find1.get());
        }else if (id==null){
            throw new Resource404NotFoundException(id+" nolu id yoktur");
        }
         */
        RegisterEntity registerEntity = null;
        if (id != null) {
            registerEntity= iRegisterRepository.findById(id)
                    .orElseThrow(() -> new Resource404NotFoundException(id + " nolu id yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("id null olarak geldi");
        }
        return entityToDto(registerEntity);
    }

    // UPDATE
    // Transaction: Create, Delete, Update
    @Override
    @Transactional
    public RegisterDto registerServiceUpdate(Long id, RegisterDto registerDto) {
        // FIND
        RegisterDto registerDtoFindDto = registerServiceFindById(id);
        RegisterEntity registerEntity = null;
        if (registerDtoFindDto != null) {
            registerEntity = dtoToEntity(registerDto);
            registerEntity.setId(id);
            registerEntity.setRegisterName(registerDto.getRegisterName());
            registerEntity.setRegisterSurname(registerDto.getRegisterSurname());
            registerEntity.setRegisterEmail(registerDto.getRegisterEmail());
            registerEntity.setRegisterPassword(registerDto.getRegisterPassword());
            registerEntity.setRegisterIsPassive(registerDto.getRegisterIsPassive());
            iRegisterRepository.save(registerEntity);
        }
        return entityToDto(registerEntity);
    }

    // DELETE BY ID
    // Transaction: Create, Delete, Update
    @Override
    @Transactional
    public RegisterDto registerServiceDeleteById(Long id) {
        // FIND
        RegisterDto registerDtoFindDto = registerServiceFindById(id);
        RegisterEntity registerEntity = null;
        if (registerDtoFindDto != null)
            iRegisterRepository.deleteById(id);
        return registerDtoFindDto;
    }
} //end class Services
