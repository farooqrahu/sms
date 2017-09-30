package com.sms.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "SUBJECT")
public class Subject extends BaseEntityAudit{
    private String subject_title;
}
