package io.ds.myaktion.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.services.CampaignService;

@RestController
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    private Logger log = LoggerFactory.getLogger(CampaignController.class);

    @GetMapping("/campaigns")
    public List<Campaign> getCampaigns() {
        log.debug("Received request to read campaigns");
        List<Campaign> campaigns = campaignService.getCampaigns();
        log.trace("Send data to client:" + campaigns);
        return campaigns;
    }

    @PostMapping("/campaigns")
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        log.debug("Received request to save a campaign");
        log.trace(campaign.toString());
        return campaignService.addCampaign(campaign);
    }

    @GetMapping("/campaigns/{campaignId}")
    public Campaign getCampaign(@PathVariable Long campaignId) {
        log.debug("Received request to read campaign");
        Campaign campaign = campaignService.getCampaign(campaignId);
        log.trace("Send data to client" + campaign);
        return campaign;
    }

    @PutMapping("/campaigns/{campaignId}")
    public Campaign updateCampaign(@RequestBody Campaign campaign, @PathVariable Long campaignId) {
        //TODO Check if campaign is complete (-> use BV) and Id is identical (or missing in campaign object)
        log.debug("Received request to update campaign");
        Campaign updatedCampaign = campaignService.updateCampaign(campaign, campaignId);
        log.trace("Send data to client" + updatedCampaign);
        return updatedCampaign;
    }

    @DeleteMapping("/campaigns/{campaignId}")
    public Campaign deleteCampaign(@PathVariable Long campaignId) {
        log.debug("Received request to delete campaign");
        Campaign deletedCampaign = campaignService.deleteCampaign(campaignId);
        log.trace("Send data to client" + deletedCampaign);
        return deletedCampaign;
    }
}
