package com.calmcodecollective.roommatematcher.controller;

import com.calmcodecollective.roommatematcher.exception.AccountNotFoundException;
import com.calmcodecollective.roommatematcher.model.roommate.Account;
import com.calmcodecollective.roommatematcher.model.roommate.AccountRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AccountController {
    private final AccountRepository repository;
    private final AccountModelAssembler assembler;

    public AccountController(AccountRepository repository, AccountModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/accounts")
    CollectionModel<EntityModel<Account>> all() {
        List<EntityModel<Account>> accounts = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // Single item

    @GetMapping("/accounts/{id}")
    EntityModel<Account> one(@PathVariable Long id) {

        Account account = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return assembler.toModel(account);
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