package com.digt.lice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import org.apache.commons.lang.RandomStringUtils;
import static org.thymeleaf.util.StringUtils.randomAlphanumeric;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    private String number;
    private String userName;

    @OneToMany (orphanRemoval = true, cascade = CascadeType.ALL) // mappedBy = "account",
//    @JsonManagedReference
    private List<License> licenses; //

    public Account(){}

    public Account(String userName) {
        this.number =  randomAlphanumeric(16).toLowerCase();
        this.userName = userName;
    }

    public void addLicense(License license){
        licenses.add(license);
        license.setAccount(this);
    }

    private void removeLicense(License license){
        licenses.remove(license);
        license.setAccount(null);
    }

    public String getUserName() {
        return userName;
    }
}
