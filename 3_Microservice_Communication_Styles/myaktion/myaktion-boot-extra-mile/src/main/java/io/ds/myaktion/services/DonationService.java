package io.ds.myaktion.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;
import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.domain.DonationRepository;
import io.ds.myaktion.exceptions.CampaignNotFoundException;

@Service
public class DonationService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private DonationRepository donationRepository;

    private Logger log = LoggerFactory.getLogger(DonationService.class);

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
}
