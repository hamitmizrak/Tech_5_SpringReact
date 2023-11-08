package com.hamitmizrak.tech5.business.dto;

import com.hamitmizrak.tech5.annotation.AnnotationRoleNameUnique;
import com.hamitmizrak.tech5.audit.AuditingAwareBaseDto;
import com.hamitmizrak.tech5.rolles.ERolles;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@Builder

public class RoleDto  extends AuditingAwareBaseDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    // ROLE NAME
    @NotEmpty(message="{role.name.validation.constraints.NotNull.message}")
    @AnnotationRoleNameUnique
    @Builder.Default
    private String roleName= ERolles.USER.getValue().toString();
} //end class
