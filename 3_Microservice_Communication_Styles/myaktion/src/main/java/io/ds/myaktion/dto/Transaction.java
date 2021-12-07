package io.ds.myaktion.dto;

public class Transaction {

    private Long campaignId;
    private Long donationId;

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getDonationId() {
        return donationId;
    }

    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }

    @Override
    public String toString() {
        return "Transaction [campaignId=" + campaignId + ", donationId=" + donationId + "]";
    }

}
