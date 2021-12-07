package io.ds.myaktion.domain;

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
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Campaign {

    @JsonProperty(access = Access.READ_ONLY)
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 4, max = 30, message = "The length of a campaign's name must be ate least 4 and at most 30 characters.")
    private String name;

    @NotNull(message = "The donation amount must be at least 1 EUR.")
    @DecimalMin(value = "1.00", message = "The donation amount must be at least 1 EUR.")
    private double donationMinimum;

    @NotNull(message = "The target amount must be at least 10 EUR.")
    @DecimalMin(value = "10.00", message = "The target amount must be at least 10 EUR.")
    private double targetAmount;

    @Embedded
    @Valid
    @AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "account_name")) })
    private Account account;

    @Valid
    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donation> donations = new LinkedList<Donation>();

    @Transient
    private double amountDonatedSoFar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public double getAmountDonatedSoFar() {
        return amountDonatedSoFar;
    }

    public void setAmountDonatedSoFar(double amountDonatedSoFar) {
        this.amountDonatedSoFar = amountDonatedSoFar;
    }

    @Override
    public String toString() {
        return "Campaign [account=" + account + ", donationMinimum=" + donationMinimum + ", id=" + id + ", name=" + name
                + ", amountDonatedSoFar=" + amountDonatedSoFar + ", targetAmount=" + targetAmount + "]";
    }
}
