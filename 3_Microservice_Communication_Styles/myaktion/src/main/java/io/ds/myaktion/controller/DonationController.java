package io.ds.myaktion.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.services.DonationService;

@RestController
public class DonationController {
    @Autowired
    private DonationService donationService;

    private Logger log = LoggerFactory.getLogger(DonationController.class);

    @PostMapping("/campaigns/{campaignId}/donations")
    public Donation addDonation(@RequestBody Donation donation, @PathVariable Long campaignId) {
        log.debug("Received request to save a donation");
        log.trace(donation.toString());
        return donationService.addDonation(donation, campaignId);
    }

    @GetMapping("/campaigns/{campaignId}/donations")
    public List<Donation> getDonations(@PathVariable Long campaignId) {
        return donationService.getDonations(campaignId);
    }
}

