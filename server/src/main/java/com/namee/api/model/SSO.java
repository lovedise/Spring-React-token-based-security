package com.namee.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by namee on 2016. 6. 14..
 */
@Data
@Entity
@Table(name = "CMZ_COMPANY_AUTH")
public class SSO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="ID")
//    private Long id;

    @Id
    @Column(name="COMPANY_CD")
    private String companyCode;

    @Column(name="ALGORISM")
    private String hashType;

    @Column(name="ROLE_CD", columnDefinition="char(2)")
    private String roll;

    @Column(name="SITE_ID")
    private String siteId;

    @Column(name="SECRET_KEY")
    private String secretKey;

    @Column(name="REGIST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @PrePersist
    protected void onCreate() {
        if (createDate == null) { createDate = new Date(); }
    }
}
