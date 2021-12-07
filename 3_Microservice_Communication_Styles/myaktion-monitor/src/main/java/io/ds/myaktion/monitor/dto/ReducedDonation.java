package io.ds.myaktion.monitor.dto;

import java.net.http.HttpHeaders;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ReducedDonation { // This class is needed in both services!

    private String donorName;
    private Double amount;
    private Long campaignId;
    private static String URL_MYAKTION_MONITOR = "http://localhost:8081/donations";

    public ReducedDonation(ReducedDonation donation) {
        this.donorName = donation.donorName;
        this.amount = donation.amount;
        this.campaignId = donation.campaignId;
    }

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

    private void sendReducedDonation(ReducedDonation donation) {
        ReducedDonation reducedDonation = new ReducedDonation(donation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.Appl);
        HttpEntity<ReducedDonation> entity = new HttpEntity<>(reducedDonation, headers);
        log.info("Send Message object: " + reducedDonation);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(URL_MYAKTION_MONITOR, entity, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                log.debug("Sent donation to myaktion-monitor successfully");
            } else {
                log.debug("Failed to send donation to myaktion-monitor. Http Status=" + response.getStatusCode());
            }
        } catch (RestClientException e) {
            // TODO Implement retry mechanism
            log.info("Failed to send donation to myaktion-monitor");
            log.error("Exception received trying to send donation:", e);
        }
    }

}
