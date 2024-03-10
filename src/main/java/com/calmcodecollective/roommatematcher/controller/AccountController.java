package com.calmcodecollective.roommatematcher.controller;

import com.calmcodecollective.roommatematcher.model.roommate.Account;
import com.calmcodecollective.roommatematcher.model.roommate.AccountRepository;

import com.calmcodecollective.roommatematcher.exception.AccountNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    private final AccountRepository repository;

    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/accounts")
    List<Account> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // Single item

    @GetMapping("/accounts/{id}")
    Account one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PutMapping("/accounts/{id}")
    Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

        return repository.findById(id)
                .map(account -> {
                    account.setHandle(newAccount.getHandle());
                    account.setName(newAccount.getName());
                    return repository.save(account);
                })
                .orElseGet(() -> {
                    newAccount.setId(id);
                    return repository.save(newAccount);
                });
    }

    @DeleteMapping("/accounts/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}