package com.digt.lice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import org.apache.commons.lang.RandomStringUtils;
import static org.thymeleaf.util.StringUtils.randomAlphanumeric;

@Entity
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account { // extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany (mappedBy="account")
// (fetch = FetchType.EAGER) // ,mappedBy = "account" orphanRemoval = true, cascade = CascadeType.ALL
//    @JsonManagedReference
//    private List<License> licenses; //
    private Set<License> licenses = new HashSet<>(); //

    private String number;

    public String getNumber() {
        return number;
    }

    private String userName;

//    public Account(){}

    public Account(String userName) {
        this.number =  randomAlphanumeric(16).toLowerCase();
        this.userName = userName;
    }

    public License addLicense(License license){
        license.setAccount(this);
        licenses.add(license);
        return license;
    }

    private void removeLicense(License license){
        licenses.remove(license);
        license.setAccount(null);
    }

    public String getUserName() {
        return userName;
    }

    public Set<License> getLicenses() {
        return licenses;
    }

    public int getTokens(){
        int tokens=0;
        for (License l : licenses)
            tokens+=l.getTokens().size();
        return tokens;
    }

    public Long getId() {
        return id;
    }
/*
    public Set<License> getLicenses() {
        return licenses;
    }
    public void setLicenses(Set<License> licenses) {
        this.licenses=licenses;
    }
    */
}
