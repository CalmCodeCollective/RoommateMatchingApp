package com.calmcodecollective.roommatematcher.model.roommate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Account {
    private @Id
    @GeneratedValue Long id;
    private String handle;
    private String firstName;
    private String lastName;

    Account() {}

    Account(String handle, String firstName, String lastName) {

        this.handle = handle;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.handle, account.handle)
                && Objects.equals(this.firstName, account.firstName) && Objects.equals(this.lastName, account.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.handle, this.firstName, this.lastName);
    }

    @Override
    public String toString() {
        return "account{" + "id=" + this.id + ", handle='" + this.handle + '\'' + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + "\'" + '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
