package com.digt.lice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class License extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
//    @JsonBackReference
//    @JsonIgnore
    private Account account;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL) // mappedBy = "license",
    @JsonManagedReference
    private Set<Token> tokens = new HashSet<>();

    private LocalDateTime date = LocalDateTime.now();

    private int amountTokens;
//    private int tokenCount = tokens.size();

    public License(){}

    public License(int tokens){
//        this.account=account;
        amountTokens=tokens;
    }

    public License(Account account){
        this.account=account;
        amountTokens=9;
    }

    private void addToken(Token token){
        tokens.add(token);
//        token.setLicense(this);
    }

    private void removeToken(Token token){
        tokens.remove(token);
//        token.setLicense(null);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
