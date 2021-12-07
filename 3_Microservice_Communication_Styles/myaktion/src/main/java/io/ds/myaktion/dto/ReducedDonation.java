package io.ds.myaktion.dto;

/* import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired; */

// import io.ds.myaktion.controller.CampaignController;
import io.ds.myaktion.domain.Donation;

public class ReducedDonation {

    private String donorName;
    private Double amount;
    private Long campaignId;
    private static String URL_MYAKTION_MONITOR = "http://localhost:8081/donations";
    
    // private Logger log = org.slf4j.LoggerFactory.getLogger(CampaignController.class);


    public ReducedDonation(Donation donation) {
        this.donorName = donation.getDonorName();
        this.amount = donation.getAmount();
        this.campaignId = donation.getCampaign().getId();
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

    public static String getURL_MYAKTION_MONITOR() {
        return URL_MYAKTION_MONITOR;
    }

    public static void setURL_MYAKTION_MONITOR(String uRL_MYAKTION_MONITOR) {
        URL_MYAKTION_MONITOR = uRL_MYAKTION_MONITOR;
    }

    @Override
    public String toString() {
        return "ReducedDonation [amount=" + amount + ", campaignId=" + campaignId + ", donorName=" + donorName + "]";
    }

    /* private void sendReducedDonation(ReducedDonation donation) {
        ReducedDonation reducedDonation = new ReducedDonation(donation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
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
            log.info("Failed to send donation to myaktion-monitor");
            log.error("Exception received trying to send donation:", e);
        }
    } */
}
