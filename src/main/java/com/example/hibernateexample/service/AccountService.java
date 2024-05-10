package com.example.hibernateexample.service;

import com.example.hibernateexample.entity.Account;
import com.example.hibernateexample.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository1) {
        this.accountRepository = accountRepository1;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
