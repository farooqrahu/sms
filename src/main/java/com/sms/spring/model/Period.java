package com.sms.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "PERIOD")
public class Period extends BaseEntityAudit{

    @Column(name = "PERIOD_NUMBER", nullable = false)
    private Long periodNumber;

}
