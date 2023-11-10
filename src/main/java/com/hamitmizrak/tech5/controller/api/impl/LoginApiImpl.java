package com.hamitmizrak.tech5.controller.api.impl;

import com.hamitmizrak.tech5.bean.PasswordEncoderBeanClass;
import com.hamitmizrak.tech5.business.dto.RegisterDto;
import com.hamitmizrak.tech5.business.services.IRegisterServices;
import com.hamitmizrak.tech5.controller.api.ILoginApi;
import com.hamitmizrak.tech5.error.ApiResult;
import com.hamitmizrak.tech5.exception.HamitMizrakException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API : Dış dünyaya açılan kapı
@RestController
@RequestMapping("/login/api/v1.0.0")
@CrossOrigin
// Authentication: Kimlik Doğrulama
// Authorization : Kimlik Yetkilendirme
public class LoginApiImpl implements ILoginApi {

    // Injection
    private final IRegisterServices iRegisterServices;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;

    // LOGIN Basic Authentication
    // http://localhost:4444/login/api/v1.0.0/authentication
    // UNUTMAAAAA: frontentte(ReactJS) yazarken username:password olarak yazmalısınız
    // Basic Authentication   => Authorization
    // @PathVariable => /
    // @RequestParam => ?
    @Override
    @PostMapping("/authentication")
    public ResponseEntity<?> login( @RequestHeader(name="Authorization",required = false) String authorization) {
        if(authorization==null){
            ApiResult apiResult= ApiResult.builder()
                    .status(401)
                    .path("/login/api/v1.0.0/authentication")
                    .message("un-authorized")
                    .build();
            // 401: UNAUTHORIZED (Yetkisiz Giriş)
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }
        log.info(authorization);
        System.out.println(authorization);

        // Dikkat: Basic sonrasında bir boşluk bırakmalısıııın
        // Basic HmtMzrk4415221==
        String basicAuthenticationSplit=authorization.split("Basic ")[1]; // HmtMzrk4415221==
        System.out.println(basicAuthenticationSplit);

        // Decoder (Çözücü)
        String base64Decoder=new String(Base64.getDecoder().decode(basicAuthenticationSplit)); // email:password
        System.out.println(base64Decoder);

        //email:password ayrıştırmak
        String email,password;
        String[] emailPasswordParseArray=base64Decoder.split(":");
        email=emailPasswordParseArray[0];    //email
        System.out.println(email);
        password=emailPasswordParseArray[1]; //password
        System.out.println(password);

       RegisterDto findRegisterEntityByEmail= (RegisterDto) iRegisterServices.loginServiceFindByEmail(email);
       // Kullanıcı yoksa
       if(findRegisterEntityByEmail==null){
           ApiResult apiResultNotFound= ApiResult.builder()
                   .path("/login/api/v1.0.0/authentication")
                   .status(404)
                   .message("Kullancı bulunmadı Kayıt gerekiyor")
                   .build();
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResultNotFound);
       }
       // Kullanıcı Pasifse
        if(findRegisterEntityByEmail!=null &&  findRegisterEntityByEmail.getRegisterIsPassive()){
            ApiResult apiResultNotFound= ApiResult.builder()
                    .path("/login/api/v1.0.0/authentication")
                    .status(401)
                    .message("Kullancı aktif değil")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResultNotFound);
        }

        // Sistemde kullancıı varsa
        String passwordHashing=findRegisterEntityByEmail.getRegisterPassword();
        // currentPassword rowsPassword
        // eğer şifreler uymazsa
        if(!passwordEncoderBeanClass.passwordEncoderMethod().matches(password,passwordHashing)){
            ApiResult apiResult= ApiResult.builder()
                    .status(401)
                    .path("/login/api/v1.0.0/authentication")
                    .message("kullanıcı veya şifre yanlış")
                    .build();
            // 401: UNAUTHORIZED (Yetkisiz Giriş)
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }
        // Eğer kullanıcı varsa , aktifse ve  kullanıcı adı-şifre doğruysa
            Map<String,Object> mapList=new HashMap<>();
            mapList.put("nickname",findRegisterEntityByEmail.getRegisterNickName());
            mapList.put("name",findRegisterEntityByEmail.getRegisterName());
            mapList.put("surname",findRegisterEntityByEmail.getRegisterSurname());
            mapList.put("email",findRegisterEntityByEmail.getRegisterEmail());
            mapList.put("password",findRegisterEntityByEmail.getRegisterPassword());
            mapList.put("remaning_number",findRegisterEntityByEmail.getRemaningNumber());
            mapList.put("is_passive",findRegisterEntityByEmail.getRegisterIsPassive());
        return ResponseEntity.ok(mapList);
    }

    // LOGOUT
    // http://localhost:4444/login/api/v1.0.0/logout
    @Override
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // import org.springframework.security.core.Authentication;
        // Sisteme Login olmuş kullanıcı bilgilerini almak
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            return ResponseEntity.ok(ApiResult.builder().path("/logout").status(200).message("Sistemden çıkış yapıldı").build());
        }
        throw  new HamitMizrakException("!!! Çıkış yapılmadı");
    }
} //end class