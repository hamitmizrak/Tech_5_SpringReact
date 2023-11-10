package com.hamitmizrak.tech5.business.dto;

import com.hamitmizrak.tech5.annotation.AnnotationUniqueEmailAddress;
import com.hamitmizrak.tech5.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Collection;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@Builder

// REGISTER
public class RegisterDto extends AuditingAwareBaseDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    // Global Variable (6)
    // Dikkat: message sonunda boşluk olmasın
    // NICKNAME
    @NotEmpty(message = "{register.nickname.validation.constraints.NotNull.message}")
    private String registerNickName;

    // NAME
    @NotEmpty(message = "{register.name.validation.constraints.NotNull.message}")
    private String registerName;

    //SURNAME
    @NotEmpty(message = "{register.surname.validation.constraints.NotNull.message}")
    private String registerSurname;

    // EMAIL
    // Kendi annotation'ımı yazdı
    @AnnotationUniqueEmailAddress
    @NotEmpty(message = "{register.email.validation.constraints.NotNull.message}")
    @Email(message = "{register.email.validation.constraints.regex.message}")
    private String registerEmail;

    // PASSWORD
    //@JsonIgnore // backentte giden veriyi saklar
    @NotEmpty(message = "{register.password.validation.constraints.NotNull.message}")
    @Size(min = 7, max = 15, message = "{register.password.validation.constraints.MinMax.NotNull.message}")
    // Hm123456@.
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$", message = "{register.password.pattern.validation.constraints.NotNull.message}")
    private String registerPassword;

    // IS ACTIVE
    @Builder.Default //default olarak kullanıcı pasif olsun admin bunu aktif yapsın
    private Boolean registerIsPassive=false;

    // REMANING NUMBER
    @Builder.Default
    private Long remaningNumber=5L;


    // ROLES (ENUM)
    // @Builder.Default // Default bileşen için kullanılır.
    // private ERoles userRoles=ERoles.USER;
    private Collection<RoleDto> roles;

    // #######################################################
    // USER DETAILS
    // Kullanıcı başlangıçta kilitli yani sisteme giremezsin(Mail ile aktif etsin)
    @Builder.Default
    private Boolean isAccountNonLocked = false;

    // Kullanıcını Hesap süresi Doldu mu ?
    @Builder.Default
    private Boolean isAccountNonExpired = true;

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Builder.Default
    private Boolean isCredentialsNonExpired = true;

    // Kullanıcı Aktif mi ? pasif mi
    @Builder.Default
    private Boolean isEnabled = true;


    //parametresiz constructor
    public RegisterDto() {
    }

    //parametreli constructor
    public RegisterDto(String registerNickName, String registerName, String registerSurname, String registerEmail, String registerPassword, Boolean registerIsPassive) {
        this.registerNickName = registerNickName;
        this.registerName = registerName;
        this.registerSurname = registerSurname;
        this.registerEmail = registerEmail;
        this.registerPassword = registerPassword;
        this.registerIsPassive = registerIsPassive;
    }

    // toString
    @Override
    public String toString() {
        return "RegisterDto{" +
                "registerNickName='" + registerNickName + '\'' +
                ", registerName='" + registerName + '\'' +
                ", registerSurname='" + registerSurname + '\'' +
                ", registerEmail='" + registerEmail + '\'' +
                ", registerPassword='" + registerPassword + '\'' +
                ", registerIsPassive='" + registerIsPassive + '\'' +
                ", id=" + id +
                ", systemCreatedDate=" + systemCreatedDate +
                ", createdUser='" + createdUser + '\'' +
                ", createdDate=" + createdDate +
                ", lastUser='" + lastUser + '\'' +
                ", lastDate=" + lastDate +
                '}';
    } //end toString
} //end class
