package io.ds.myaktion.monitor.dto;

public class ReducedDonation { // This class is needed in both services!

    private String donorName;
    private Double amount;
    private Long campaignId;

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public String toString() {
        return "ReducedDonation [amount=" + amount + ", campaignId=" + campaignId + ", donorName=" + donorName + "]";
    }

}
