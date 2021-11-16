package io.dis.myaktion.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Campaign {
    @Id 
    @GeneratedValue
    Long id;

    private String name;
    private double donationMinimum;
    private double targetAmount;
    
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations = new LinkedList<>();
    
    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "name", column = @Column(name="account_name")))
    private Account account;

    public Campaign(){}
    
    public Campaign(String name, double donationMinimum, double targetAmount){
        this.name = name;
        this.donationMinimum = donationMinimum;
        this.targetAmount = targetAmount; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDonationMinimum() {
        return donationMinimum;
    }

    public void setDonationMinimum(double donationMinimum) {
        this.donationMinimum = donationMinimum;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    // addDonation
    public void addDonations(Donation donation) {
        this.donations.add(donation) ;
    }
    
    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Campaign [donationMinimum=" + donationMinimum + ", name=" + name + ", targetAmount=" + targetAmount
                + "]";
    }
}


