package com.digt.lice.repositories;

import com.digt.lice.model.Account;
import com.digt.lice.service.AccountService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByNumber(String number);
}
