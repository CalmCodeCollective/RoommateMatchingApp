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
    private String name;

    Account() {}

    Account(String handle, String name) {

        this.handle = handle;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.handle, account.handle)
                && Objects.equals(this.name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.handle, this.name);
    }

    @Override
    public String toString() {
        return "account{" + "id=" + this.id + ", handle='" + this.handle + '\'' + ", name='" + this.name + '\'' + '}';
    }
}
