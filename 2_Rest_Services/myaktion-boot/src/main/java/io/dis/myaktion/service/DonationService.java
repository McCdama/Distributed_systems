package io.dis.myaktion.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.dis.myaktion.domain.Campaign;
import io.dis.myaktion.domain.Donation;
import io.dis.myaktion.repository.CampaignRepository;
import io.dis.myaktion.repository.DonationRepository;

public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    public void addDonation (Donation donation, Long campaignId){
     Optional<Campaign> results =  campaignRepository.findById(campaignId);
    
     // check if empty
     if (results.isEmpty()) {
         throw new NullPointerException();
     }
     // Create a campaign object
     Campaign exists = results.get(); // otherwise throws NoSuchElementException.

     // Add a donation object
    donation.setCampaign(exists);

    // Save the campaign object to the database
     Donation save = donationRepository.save(donation);
     // campaignRepository.saveAndFlush(exists);
    donationRepository.save(save);
    }
}
