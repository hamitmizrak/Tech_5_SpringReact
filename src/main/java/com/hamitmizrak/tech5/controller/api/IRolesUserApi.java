package com.hamitmizrak.tech5.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IRolesUserApi<D> {

    // INJECTION

    // SPEED DATA
    public ResponseEntity<List<D>> registerApiSpeedData(Long key);

    // ALL DELETE
    public ResponseEntity<?> registerApiDeleteAll();

    /////////////////////////////////////////////////////////////
    // LOGIN
    // FIND SURNAME
    public ResponseEntity<?> userEmailFindRoles(String emailAddress);

    ////////////////////////////////////////////////////////////

    // C R U D
    // CREATE
    public ResponseEntity<?> rolesApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> rolesApiList();

    // FIND
    public ResponseEntity<?> rolesApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> rolesApiUpdate(Long id, D d);

    // DELETE
    public ResponseEntity<?> rolesApiDeleteById(Long id);

} //end interface


