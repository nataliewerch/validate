package org.example.controller;

import org.example.entity.Account;
import org.example.entity.User;
import org.example.service.AccountService;
import org.example.service.TransferService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @GetMapping
    List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    Account getAccountById(@PathVariable long id) {
        return accountService.getById(id);
    }

    @PostMapping
    ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.save(account));
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam long userId,
                         @RequestParam long acc1Id,
                         @RequestParam long acc2Id,
                         @RequestParam double amount) throws IllegalAccessException {
        User user = userService.getById(userId);
        Account acc1 = null;
        Account acc2 = null;
        for (Account account : user.getAccounts()) {
            if (account.getId() == acc1Id) {
                acc1 = account;
            }
            if (account.getId() == acc2Id) {
                acc2 = account;
            }
        }
        if (acc1 == null || acc2 == null) {
            throw new IllegalArgumentException("Account not found");
        }
        transferService.transfer(acc1.getId(), acc2.getId(), amount);
    }
}
