package com.digt.lice.repositories;

import com.digt.lice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositories extends JpaRepository<Account, Long>{
    Account findAccountByNumber(String number);
}
