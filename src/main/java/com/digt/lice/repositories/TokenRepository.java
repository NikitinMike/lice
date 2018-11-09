package com.digt.lice.repositories;

import com.digt.lice.model.License;
import com.digt.lice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByLicense(License license);
}
