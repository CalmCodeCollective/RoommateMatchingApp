package com.calmcodecollective.roommatematcher.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Could not find account " + id);
    }
}
