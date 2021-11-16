package io.dis.myaktion.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Account {

    private String iban;
    private String name;
    private String nameOfBank;

    public Account() {}

    public Account(String iban, String name, String nameOfBank) {
        this.iban = iban;
        this.name = name;
        this.nameOfBank = nameOfBank;
    }

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
