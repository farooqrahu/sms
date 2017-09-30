package com.sms.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course extends BaseEntityAudit{

    @Column(name = "COURSE_TITLE")
    private String course_title;

    /*@OneToMany
    @JoinTable(name = "SUBJECT_ID")
    private Subject subject;*/

}
