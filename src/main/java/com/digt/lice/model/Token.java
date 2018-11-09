package com.digt.lice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.thymeleaf.util.StringUtils.randomAlphanumeric;

@Entity
@Data
public class Token extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private License license;

    private Boolean active;

    @Column(length = 999)
    private String JWT;

    private String userName;
    private LocalDateTime date = LocalDateTime.now();

    public Token(License l){
        license=l;
        userName=l.getAccount().getUserName().toUpperCase();
        JWT=randomAlphanumeric(32).toUpperCase();
    }

}
