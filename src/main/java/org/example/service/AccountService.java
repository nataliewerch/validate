package org.example.service;

import org.example.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account save(Account account);

    Account getById(long id);
}
