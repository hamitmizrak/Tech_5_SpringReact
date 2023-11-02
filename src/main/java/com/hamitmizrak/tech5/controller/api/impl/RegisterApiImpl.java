package com.hamitmizrak.tech5.controller.api.impl;

import com.hamitmizrak.tech5.business.dto.RegisterDto;
import com.hamitmizrak.tech5.business.services.IRegisterServices;
import com.hamitmizrak.tech5.controller.api.IRegisterApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API : Dış dünyaya açılan kapı
@RestController
@RequestMapping("/register/api/v1.0.0")
@CrossOrigin
public class RegisterApiImpl implements IRegisterApi<RegisterDto> {

    // Injection
    private final IRegisterServices iRegisterServices;


    // SPEED DATA
    // http://localhost:4444/register/api/v1.0.0/speed/data/5
    @Override
    @GetMapping("/speed/data/{id}")
    public ResponseEntity<List<RegisterDto>> registerApiSpeedData( @PathVariable(name="id") Long key) {
        return ResponseEntity.ok(iRegisterServices.registerServiceSpeedData(key)) ;
    }

    // ALL DELETE
    // http://localhost:4444/register/api/v1.0.0/delete/all
    @Override
    @GetMapping("/delete/all")
    public ResponseEntity<?> registerApiDeleteAll() {
        return ResponseEntity.ok(iRegisterServices.registerServiceDeleteAll());
    }

    /////////////////////////////////////////////////////////////
    // LOGIN
    // FIND SURNAME
    // http://localhost:4444/register/api/v1.0.0/search?surname=mizrak
    @Override
    @GetMapping("/search")
    public ResponseEntity<?> loginApiFindBySurname(@RequestParam(name="surname")  String surname) {
        return ResponseEntity.ok(iRegisterServices.loginServiceFindBySurname(surname));
    }

    ////////////////////////////////////////////////////////////
    // C R U D
    // CREATE
    // http://localhost:4444/register/api/v1.0.0/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> registerApiCreate( @Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(iRegisterServices.registerServiceCreate(registerDto));
    }

    // LIST
    // http://localhost:4444/register/api/v1.0.0/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<RegisterDto>> registerApiList() {
        return ResponseEntity.ok().body(iRegisterServices.registerServiceList());
    }

    // FIND BY ID
    // http://localhost:4444/register/api/v1.0.0/find/1
    @Override
    @GetMapping(value="/find/{id}")
    public ResponseEntity<?> registerApiFindById(@PathVariable(name="id")  Long id) {
        return ResponseEntity.ok().body(iRegisterServices.registerServiceFindById(id));
    }

    // UPDATE
    // http://localhost:4444/register/api/v1.0.0/update/1
    @Override
    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> registerApiUpdate( @PathVariable(name="id") Long id,  @Valid @RequestBody  RegisterDto registerDto) {
        return ResponseEntity.status(200).body(iRegisterServices.registerServiceFindById(id));
    }

    // DELETE BY ID
    // http://localhost:4444/register/api/v1.0.0/delete/1
    @Override
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> registerApiDeleteById(@PathVariable(name="id")  Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iRegisterServices.registerServiceDeleteById(id));
    }

} //end class
