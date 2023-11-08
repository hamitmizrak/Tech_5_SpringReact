package com.hamitmizrak.tech5.annotation;

import com.hamitmizrak.tech5.data.repository.IRegisterRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

// ConstraintValidator
public class RoleNameUniqueValidation implements ConstraintValidator<AnnotationRoleNameUnique,String> {

    // INJECTION
    private final IRegisterRepository iRegisterRepository;

    @Override
    public void initialize(AnnotationRoleNameUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // DATABASE SORGUSU
    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext constraintValidatorContext) {
        Boolean isEmailAddress=iRegisterRepository.findByRegisterEmail(emailAddress).isPresent();
        //Eğer email address sistemde varsa
        if(isEmailAddress){
            return false;
        }
        return true;
    } //end isValid
} //end class
