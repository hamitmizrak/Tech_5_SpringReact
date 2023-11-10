package com.hamitmizrak.tech5.data.entity;

import com.hamitmizrak.tech5.data.embeddable.UserDetailsEmbeddable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder

// ENTITY
@Entity(name="Users")
@Table(name = "registers")
public class RegisterEntity  implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    // ManyToMany için kendi ID buraya yazdım BaseEntity yazmadım
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Global Variable (6)
    // Dikkat: message sonunda boşluk olmasın
    // unique = true,
    @Column(name = "register_nick_name",nullable = false, updatable = false,insertable = true,length = 100)
    private String registerNickName;

    @Column(name = "register_name",columnDefinition = "varchar(255) default 'adınızı girmediniz'")
    private String registerName;

    @Column(name = "register_surname")
    private String registerSurname;

    @Column(name = "register_email")
    private String registerEmail;

    @Column(name = "register_password")
    private String registerPassword;

    @Column(name = "active")
    private Boolean registerIsPassive=false;

    @Column(name = "remaning_number")
    private Long remaningNumber;

    // PAGE AUTHORIZATION (O Sayfaya yetkili Kişiler)
    @Column(name="page_authorization")
    private boolean pageAuthorization;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="system_created_date")
    private Date systemCreatedDate;
    //private LocalDate systemDate;


    // ROLE ENTITIY
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
    //2.YOL
	/*
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = {
	            @JoinColumn(name = "user_id",referencedColumnName = "user_id")},
	            inverseJoinColumns = {
	                            @JoinColumn(name = "roles_id",referencedColumnName = "roles_id")
	                            }
	             )
	 private List<RoleEntity> roles;
	   */

    // #######################################################
    // USER DETAILS
    // @Embedded
    // @Embeddable
    // @EmbeddedId
    @Embedded
    private UserDetailsEmbeddable userDetailsEmbeddable=new UserDetailsEmbeddable();

} //end class
