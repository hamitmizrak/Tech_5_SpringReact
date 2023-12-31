package com.hamitmizrak.tech5.audit;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

//LOMBOK
@Getter
@Setter

// abstract CLASS
abstract public class AuditingAwareBaseDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    // ID
    protected  Long id;

    // DATE
    @Builder.Default // Lombok Default
    protected Date systemCreatedDate=new Date(System.currentTimeMillis());

    //////////////////////////////////////////////////////////////////
    // AUDITING
    // Kim ekledi
    //@JsonIgnore // Backte giden veride bunu göstermek
    protected String createdUser;

    // Kim ne zaman ekledi
    //@JsonIgnore
    protected Date createdDate;

    // Kim güncelledi
    //@JsonIgnore
    protected String lastUser;

    // Kim ne zaman güncelledi
    //@JsonIgnore
    protected Date lastDate;

} //end class
