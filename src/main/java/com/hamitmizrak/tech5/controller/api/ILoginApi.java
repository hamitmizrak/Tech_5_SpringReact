package com.hamitmizrak.tech5.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

// Authentication: Kimlik Doğrulama
// Authorization : Kimlik Yetkilendirme
public interface ILoginApi {

    // LOGIN Basic Authentication
    public ResponseEntity<?> login(String authorization);

    // LOGOUT
    // import jakarta.servlet.http.HttpServletRequest;
    // import jakarta.servlet.http.HttpServletResponse;
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response);
}
