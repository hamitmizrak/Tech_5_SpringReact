package com.hamitmizrak.tech5.business.dto;


import com.hamitmizrak.tech5.annotation.AnnotationRoleNameUnique;
import com.hamitmizrak.tech5.audit.AuditingAwareBaseDto;
import com.hamitmizrak.tech5.rolles.ERolles;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// @SneakyThrows
public class RoleDto extends AuditingAwareBaseDto implements Serializable {

    // SERILEŞTIRME
    public static final long serialVersionUID=1L;

    // ROLE ID
    private Long rolesId;

    // ROLE NAME
    // Eğer Bir kullanıcı Admin belirlememişse Bu kullanıcı USER olduk
    @NotEmpty(message = "{role.name.validation.constraints.NotNull.message}")
    @AnnotationRoleNameUnique // Kendi Annotation RolName yazdım
    @Builder.Default
    private String roleName= ERolles.USER.toString();

} //end class
