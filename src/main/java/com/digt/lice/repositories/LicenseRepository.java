package com.digt.lice.repositories;

import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findAllByAccount(Account account);
    License findAllById(Long id);
}
