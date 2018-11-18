package com.digt.lice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.thymeleaf.util.StringUtils.randomAlphanumeric;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Token { // extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private License license;

    private Boolean active;

    @Column(length = 999)
    private String JWT;

    private String userName;
    private LocalDateTime date = LocalDateTime.now();

//    public Token(){}

    public Token(License l){
        license=l;
        userName=l.getAccount().getUserName().toUpperCase();
        JWT=randomAlphanumeric(32).toUpperCase();
    }

}
