package io.ds.myaktion.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;
import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.domain.DonationRepository;
import io.ds.myaktion.dto.Transaction;
import io.ds.myaktion.exceptions.CampaignNotFoundException;
import io.lettuce.core.RedisCommandTimeoutException;

@Service
public class DonationService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private RedisTemplate<String, Transaction> redisTemplate;

    private Logger log = LoggerFactory.getLogger(DonationService.class);


    /* 
        Test the communication!
        1. Start Redis server
        2. Start myaktion-bank
        3. Start myaktion --> Check log of myaktion-bank
        Add futher donations to test functionality (curl/swagger)
    
    */
    public Donation addDonation(Donation donation, Long campaignId) {
        Optional<Campaign> result = campaignRepository.findById(campaignId);
        if (result.isEmpty()) {
            CampaignNotFoundException e = new CampaignNotFoundException("Campaign with " + campaignId + " not found.");
            log.error(e.getMessage(), e);
            throw e;
        }
        Campaign existingCampaign = result.get();
        donation.setCampaign(existingCampaign);
        Donation savedDonation = donationRepository.save(donation);
        log.trace("Saved Donation: " + savedDonation.toString());
        // log.info("Sending donation to myaktion-monitor");
        // sendReducedDonation(donation);
        // create and send bank transaction
        Transaction transaction = new Transaction();
        transaction.setCampaignId(donation.getCampaign().getId());
        transaction.setDonationId(donation.getId());
        try {
            redisTemplate.convertAndSend("processTransaction", transaction);
        } catch (RedisCommandTimeoutException e) {
            // Implement a retry mechanism.
        }
        return savedDonation;
    }

    public List<Donation> getDonations(Long campaignId) {
        log.debug("Load Donations from Campaign with Id: " + campaignId);
        Optional<Campaign> result = campaignRepository.findById(campaignId);
        if (result.isEmpty()) {
            CampaignNotFoundException e = new CampaignNotFoundException("Campaign with " + campaignId + " not found.");
            log.error(e.getMessage(), e);
            throw e;
        }
        Campaign existingCampaign = result.get();
        log.trace("Loaded Donations from Campaign with Id: " + existingCampaign.getDonations());
        return existingCampaign.getDonations();
    }

    // private void sendReducedDonation(Donation donation) {
    //     ReducedDonation reducedDonation = new ReducedDonation(donation);
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);
    //     HttpEntity<ReducedDonation> entity = new HttpEntity<>(reducedDonation, headers);
    //     log.info("Send Message object: " + reducedDonation);
    //     RestTemplate restTemplate = new RestTemplate();
    //     try {
    //         ResponseEntity<String> response = restTemplate.postForEntity(URL_MYAKTION_MONITOR, entity, String.class);
    //         if (response.getStatusCode().equals(HttpStatus.OK)) {
    //             log.debug("Sent donation to myaktion-monitor successfully");
    //         } else {
    //             log.debug("Failed to send donation to myaktion-monitor. Http Status=" + response.getStatusCode());
    //         }
    //     } catch (RestClientException e) {
    //         log.info("Failed to send donation to myaktion-monitor");
    //         log.error("Exception received trying to send donation:", e);
    //     }
    // }
}
