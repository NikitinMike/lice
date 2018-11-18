package com.digt.lice.service;

import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import com.digt.lice.repositories.AccountRepository;
import com.digt.lice.repositories.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.random;

@Service
public class LicenseService {

    @Autowired LicenseRepository licenseRepository;

    public License create(Account account) {
        License license=new License(account);
//        license.setTokensAmount((int) (Math.random()*9)+1);
        licenseRepository.save(license);
        return license;
    }

    public License get(Long id) {
        return licenseRepository.getOne(id);
    }

    public List<License> list(Account account) {
//        System.out.println("LIST");
        return licenseRepository.findAllByAccount(account);
    }

    public boolean delete(Long id) {
        License license = get(id);
        if (license == null) return false;
        if (license.getTokens().size() > 0) return false;
        licenseRepository.delete(license);
        return true;
    }

}
