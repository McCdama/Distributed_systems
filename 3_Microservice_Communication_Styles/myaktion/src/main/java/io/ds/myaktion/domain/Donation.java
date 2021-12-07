package io.ds.myaktion.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Donation {

    @JsonProperty(access = Access.READ_ONLY)
    @Id
    @GeneratedValue
    private Long id;
 
    @Valid
    @Embedded
    private Account account;

    @NotNull(message = "The donation must be at least 1 EUR.")
    @DecimalMin(value = "1.00", message = "The donation must be at least 1 EUR.")
    private double amount;

    @NotNull
    private boolean receiptRequested;

    @NotNull
    @Size(min = 5, max = 40, message = "The length of the donor's name must be at least 5 and at most 40 characters.")
    private String donorName;

    @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status = Status.IN_PROCESS;

    @Valid
    @JsonIgnore
    @ManyToOne
    private Campaign campaign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isReceiptRequested() {
        return receiptRequested;
    }

    public void setReceiptRequested(boolean receiptRequested) {
        this.receiptRequested = receiptRequested;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public enum Status {
        TRANSFERRED, IN_PROCESS;
    }

    @Override
    public String toString() {
        return "Donation [account=" + account + ", amount=" + amount + ", donorName=" + donorName + ", id=" + id
                + ", receiptRequested=" + receiptRequested + ", status=" + status + "]";
    }
}
