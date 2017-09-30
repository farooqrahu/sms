package com.sms.spring.model;

import javax.persistence.*;


@Entity
@Table(name = "STUDENT_CLASS")
public class StudentClass extends BaseEntityAudit {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSE_ID")
    private Course course;
}
