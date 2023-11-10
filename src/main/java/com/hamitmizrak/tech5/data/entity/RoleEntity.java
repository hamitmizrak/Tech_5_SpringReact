package com.hamitmizrak.tech5.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder

// ENTITY
@Entity(name="Roles")
@Table(name = "roles")
public class RoleEntity  extends BaseEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    @Column(name = "role_name",columnDefinition = "varchar(255) default 'Role Adınızı girmediniz")
    private String roleName;
}
