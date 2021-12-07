package io.ds.myaktion.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Account {
    @NotNull
    @Pattern(regexp = "[A-Z]{2}[0-9]{2}[A-Z0-9]{12,30}", message = "An IBAN consists of two upper case letters, followed by two digits and 12 to 30 alphanumeric characters")
    private String iban;
    @NotNull
    @Size(min = 5, max = 60, message = "The owner's name of an account must have a length of at least 5 and at most 60 characters.")
    private String name;
    @NotNull
    @Size(min = 4, max = 40, message = "The bank's name must have a length of at least 4 and at most 40 characters.")
    private String nameOfBank;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    @Override
    public String toString() {
        return "Account [iban=" + iban + ", name=" + name + ", nameOfBank=" + nameOfBank + "]";
    }
}
