package io.dis.myaktion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.dis.myaktion.domain.Campaign;
import io.dis.myaktion.domain.Donation;
import io.dis.myaktion.exceptions.CampaignNotFoundException;
import io.dis.myaktion.repository.CampaignRepository;
import io.dis.myaktion.repository.DonationRepository;

public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    public Donation addDonation(Donation donation, Long campaignId) {
        Optional<Campaign> results = campaignRepository.findById(campaignId);

        // check if empty
        if (results.isEmpty())  throw new CampaignNotFoundException("Not found");

        Campaign existCamp = results.get();

        // Add a donation object
        donation.setCampaign(existCamp);

        // Save the campaign object to the database
        return donationRepository.save(donation);

    }
}
