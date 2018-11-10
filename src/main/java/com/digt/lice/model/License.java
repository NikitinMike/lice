package com.digt.lice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class License extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
//    @JsonBackReference
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "license", orphanRemoval = true, cascade = CascadeType.ALL) // 
    @JsonManagedReference
    private Set<Token> tokens = new HashSet<>();

    private LocalDateTime date = LocalDateTime.now();
    private LocalDateTime expire = date.plusDays((long)(Math.random()*100)); // plusMonths(1);

    private int amountTokens;
//    private int tokenCount = tokens.size();

//    public License(){}

    public String getExpire() {
        return expire.format(DateTimeFormatter.ofPattern("d MMM uuuu")); //
    }

    public boolean allowIsuueTokens() {
        return amountTokens>tokens.size();
    }

    public License(int tokens){
//        this.account=account;
        amountTokens=tokens;
    }

    public License(Account account){
        this.account=account;
//        amountTokens=9;
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

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public void setTokensAmount(int amountTokens) {
        this.amountTokens=amountTokens;
    }
}
