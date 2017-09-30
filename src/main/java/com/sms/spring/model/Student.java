package com.sms.spring.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
public class Student extends BaseEntityAudit{

    @Column(name="FIRST_NAME")
    private String first_name;

    @Column(name="MIDDLE_NAME")
    private String MIDDLE_name;

    @Column(name="LAST_NAME")
    private String LAST_name;

    @Column(name = "ROLE_NO")
    private String roleNo;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "AGE")
    private String age;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "PHONE_NO")
    private String phoneNo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "STUDENT_CLASS_ID")
    private StudentClass std_Class;




}
