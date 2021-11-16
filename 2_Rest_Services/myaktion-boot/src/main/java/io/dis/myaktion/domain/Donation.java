package io.dis.myaktion.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Donation {
    @Id
    @GeneratedValue
    Long id;

    @Embedded
    private Account account;

    private double amount;
    private boolean receiptRequested;
    private String donorName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Campaign campaign;

    /* @OneToMany
    (mappedBy = "campaigns", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations = new LinkedList<>(); */

    
    public Donation(){}    
    public Donation(double amount, boolean receiptRequested, String donorName, Status status){
        this.amount = amount;
        this.receiptRequested = receiptRequested;
        this.donorName = donorName;
        this.status = Status.IN_PROCESS;
    }

    public Campaign getCampaign() {
        return campaign;
    }
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
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

    @Override
    public String toString() {
        return "Donation [amount=" + amount + ", donorName=" + donorName + ", receiptRequested=" + receiptRequested
                + ", status=" + status + "]";
    }

    
    
}
