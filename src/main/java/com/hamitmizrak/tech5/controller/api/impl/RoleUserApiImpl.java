package com.hamitmizrak.tech5.controller.api.impl;

import com.hamitmizrak.tech5.business.dto.RoleDto;
import com.hamitmizrak.tech5.business.services.IRolesUserServices;
import com.hamitmizrak.tech5.controller.api.IRolesUserApi;
import com.hamitmizrak.tech5.data.repository.IRoleRepository;
import com.hamitmizrak.tech5.error.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API : Dış dünyaya açılan kapı
@RestController
@RequestMapping("/roles/api/v1.0.0")
@CrossOrigin
public class RoleUserApiImpl implements IRolesUserApi<RoleDto> {

    // Injection
    private final IRolesUserServices iRolesUserServices;
    private final IRoleRepository iRoleRepository;

    /////////////////////////////////////////////////////////////////////
    // SPEED DATA
    @Override
    public ResponseEntity<List<RoleDto>> registerApiSpeedData(Long key) {
        return null;
    }

    // DELETE ALL
    @Override
    public ResponseEntity<?> registerApiDeleteAll() {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // http://localhost:4444/roles/api/v1.0.0/roles/hamitmizrak@gmail.com
    @Override
    @GetMapping("/roles/{email}") //RequestParam
    public ResponseEntity<?> userEmailFindRoles(@PathVariable(name="email",required = false) String emailAddress) {
        return (ResponseEntity<?>) iRolesUserServices.userEmailFindRoles(emailAddress);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // CREATE
    // http://localhost:4444/roles/api/v1.0.0/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> rolesApiCreate(@Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(iRolesUserServices.rolesServiceCreate(roleDto));
    }

    // LIST
    // http://localhost:4444/roles/api/v1.0.0/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<RoleDto>> rolesApiList() {
        return ResponseEntity.ok(iRolesUserServices.rolesServiceList());
    }

    // FIND BY ID
    // http://localhost:4444/roles/api/v1.0.0/find/1
    @Override
    @GetMapping({"/find","/find/{id}"})
    public ResponseEntity<?> rolesApiFindById(@PathVariable(name="id",required = false) Long id) {

        if(id==null){
            log.error("API => 404 Not Found");
            return ResponseEntity.notFound().build();
        }else if(id==0){
            log.error("API => 400 Bad Request");
            ApiResult apiResult= ApiResult.builder()
                    .path("/roles/api/v1.0.0/find/1")
                    .message("Bad Request")
                    .status(400)
                    .build();
            return ResponseEntity.badRequest().body(apiResult);
        }
        return ResponseEntity.ok(iRolesUserServices.rolesServiceFindById(id));
    }

    // UPDATE
    // http://localhost:4444/roles/api/v1.0.0/update/1
    @Override
    @PutMapping({"/update","/update/{id}"})
    public ResponseEntity<?> rolesApiUpdate(@PathVariable(name="id",required = false) Long id, @Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(iRolesUserServices.rolesServiceUpdate(id,roleDto));
    }

    // DELETE BY ID
    // http://localhost:4444/roles/api/v1.0.0/delete/1
    @Override
    @DeleteMapping({"/delete","/delete/{id}"})
    public ResponseEntity<?> rolesApiDeleteById(@PathVariable(name="id",required = false) Long id) {

        return ResponseEntity.ok(iRolesUserServices.rolesServiceDeleteById(id));
    }

} //end class
