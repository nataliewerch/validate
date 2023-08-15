package org.example.service;

import org.example.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountService accountService;

    @Override
    public void transfer(long acc1Id, long acc2Id, double amount) throws IllegalAccessException {
        Account accountOne = accountService.getById(acc1Id);
        Account accountTwo = accountService.getById(acc2Id);

        if (accountOne.getAmount() - amount < 0) {
            throw new IllegalAccessException("Not enough amount on account one!!!");
        }
        accountOne.setAmount(accountOne.getAmount() - amount);
        accountService.save(accountOne);

        accountTwo.setAmount(accountTwo.getAmount() + amount);
        accountService.save(accountTwo);
    }
}
