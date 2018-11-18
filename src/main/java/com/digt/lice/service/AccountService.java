package com.digt.lice.service;

import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import com.digt.lice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired AccountRepository accountRepository;
    @Autowired LicenseService licenseService;

    public Account create(String userName) {
        Account account=new Account(userName);
        accountRepository.save(account);
        return account;
    }

    public Account get(String accountNumber) {
        return accountRepository.findAccountByNumber(accountNumber);
    }

    public List<Account> list() {
//        System.out.println("LIST");
        return accountRepository.findAll();
    }

    public boolean delete(String accountNumber) {
        Account account = get(accountNumber);
        if (account == null) return false;
        if (account.getLicenses().size() > 0) return false;
        accountRepository.delete(account);
        return true;
    }

    public License addLicense(Account account){
//        System.out.println(account.getNumber());
        License license = licenseService.create(account);
        return license;
    }

}
