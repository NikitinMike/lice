package com.digt.lice.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//@MappedSuperclass
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    public BaseEntity(){}
    
    public Long getId() {
        return id;
    }
}
