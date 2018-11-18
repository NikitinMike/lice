package com.digt.lice.service;

import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import com.digt.lice.model.Token;
import com.digt.lice.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    @Autowired TokenRepository tokenRepository;

    public Token create(License license) {
        Token token=new Token(license);
        tokenRepository.save(token);
        return token;
    }

    public Token get(Long id) {
        return tokenRepository.getOne(id);
    }

    public List<Token> list(License license) {
        return tokenRepository.findAllByLicense(license);
    }

    public boolean delete(Long id) {
        Token token = get(id);
        if (token == null) return false;
        tokenRepository.delete(token);
        return true;
    }

}
