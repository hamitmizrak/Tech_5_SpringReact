package com.hamitmizrak.tech5.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Sisteme giriş yapmış kulalnıcı bilgisini almak
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface UserLoginSystem {

} //end class