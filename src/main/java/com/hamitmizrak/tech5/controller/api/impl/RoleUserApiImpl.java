package com.hamitmizrak.tech5.controller.api.impl;


import com.hamitmizrak.tech5.business.dto.RoleDto;
import com.hamitmizrak.tech5.business.services.IRolesUserService;
import com.hamitmizrak.tech5.controller.api.IRolesUserApi;
import com.hamitmizrak.tech5.data.entity.RegisterEntity;
import com.hamitmizrak.tech5.data.repository.IRegisterRepository;
import com.hamitmizrak.tech5.error.ApiResult;
import com.hamitmizrak.tech5.rolles.ERolles;
import com.hamitmizrak.tech5.utils.FrontendPortUrl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// REST
@RestController
@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/roles/api/v1")
public class RoleUserApiImpl implements IRolesUserApi {

    // INJECTION
    private final IRolesUserService iRolesUserService;
    private final IRegisterRepository  iUserRepository;
    // private final IBlogRepository iBlogRepository;

    // ERROR
    private ApiResult apiResult;


    // ROLES CREATE
    // http://localhost:2222/roles/api/v1/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<RoleDto> rolesCreate(@Valid @RequestBody RoleDto roleDto) {
        //Sisteme olan kullancılar
        /**
         * Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
         * if(authentication!=null && authentication.isAuthenticated()){
         * System.out.println(authentication.getName());
         * System.out.println(authentication.getPrincipal());
         * }*/
        return ResponseEntity.ok(iRolesUserService.rolesCreate(roleDto));
    }

    // ROLES LIST
    // http://localhost:2222/roles/api/v1/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<RoleDto>> rolesList() {
        return ResponseEntity.ok(iRolesUserService.rolesList());
    }

    // FIND
    // http://localhost:2222/roles/api/v1/find
    // http://localhost:2222/roles/api/v1/find/0
    // http://localhost:2222/roles/api/v1/find/-1
    // http://localhost:2222/roles/api/v1/find/1
    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> rolesApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            //return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            apiResult = new ApiResult(400, "localhost:2222/blog/api/v1/blog/0", "Kötü istek", "Bad Request");
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:2222/blog/api/v1/blog/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        System.out.println(iRolesUserService.rolesFind(id));
        return ResponseEntity.ok(iRolesUserService.rolesFind(id));
    }


    // UPDATE
    // http://localhost:2222/roles/api/v1/update/1
    @Override
    @PutMapping({"/update", "/update/{id}"})
    public ResponseEntity<RoleDto> rolesUpdate(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(iRolesUserService.rolesUpdate(id, roleDto));
    }


    //////////////////////////////////////////////////////////////////////////////////////////////

    // Email adresinden kullanıcı rolünü bulmak
    // http://localhost:2222/roles/api/v1/roles/hamitmizrak1@gmail.com
    @Override
    @GetMapping("/roles/{email}")
    public ResponseEntity<RoleDto> userEmailFindRoles(@PathVariable(name="email") String emailAddress) { //@RequestParam
        return ResponseEntity.ok(iRolesUserService.userEmailFindRoles(emailAddress));
    }


    // DELETE
    // http://localhost:2222/roles/api/v1/delete/5
    @Override
    @DeleteMapping({"/delete", "/delete/{id}"})
    public ResponseEntity<?> rolesDelete(@PathVariable(name = "id", required = false) Long id) {

        //System.out.println(iBlogRepository.mySpecialBlogList());

        StringBuilder stringBuilder=new StringBuilder();
        List<RegisterEntity> list=iUserRepository.findAllByUserInJoinRolesRoleName1(ERolles.USER.toString()); // "USER"
        list.forEach((temp)->{
            System.out.println(temp);
            stringBuilder.append(temp.getRegisterEmail()+" ");
        });
        if(list.size()!=0){
            apiResult = new ApiResult(400, "localhost:2222/roles/api/v1/delete", "Bu Rolü silemezsiniz." +"Öncelikle Kullanıcılardan, "+stringBuilder+ "Silmelisin", "Bad Request");
            //return ResponseEntity.badRequest().build();
            return  ResponseEntity.status(400).body(apiResult);
        }
        return  ResponseEntity.ok(iRolesUserService.rolesDelete(id));
    } //end delete

} // end class