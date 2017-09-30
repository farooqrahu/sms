package com.sms.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "NON_TEACHING_STAFF")
public class NonTeachingStaff extends BaseEntityAudit implements Serializable{

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "DESIGNATION", nullable = false)
    private String designation;


}
