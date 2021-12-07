package io.ds.myaktion.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;
import io.ds.myaktion.exceptions.CampaignNotFoundException;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    private Logger log = LoggerFactory.getLogger(CampaignService.class);

    public Campaign addCampaign(Campaign campaign) {
        Campaign savedCampaign = campaignRepository.save(campaign);
        log.trace("Saved campaign: "+ savedCampaign.toString());
        return  savedCampaign;
    }

    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        campaigns.forEach((campaign)->{
            double amountDonatedSoFar = campaignRepository.getAmountDonatedSoFar(campaign.getId());
            campaign.setAmountDonatedSoFar(amountDonatedSoFar);
        });
        log.trace("Read campaigns: "+ campaigns.toString());
        return campaigns;
    }

    public Campaign getCampaign(Long campaignId) {
        log.debug("Read campaign with Id: "+ campaignId);
        Optional<Campaign> optional = campaignRepository.findById(campaignId);
        if (!optional.isPresent()) throw new CampaignNotFoundException("Campaign with "+campaignId+" not found.");
        Campaign campaign = optional.get();
        double amountDonatedSoFar = campaignRepository.getAmountDonatedSoFar(campaignId);
        campaign.setAmountDonatedSoFar(amountDonatedSoFar);
        log.trace("Read campaign: "+ campaign.toString());
        return campaign;
    }

    public Campaign updateCampaign(Campaign campaign, Long campaignId) {
        log.debug("Updated campaign with Id: "+ campaignId);
        Optional<Campaign> optional = campaignRepository.findById(campaignId);        
        if (!optional.isPresent()) throw new CampaignNotFoundException("Campaign with "+campaignId+" not found.");
        Campaign oldCampaign = optional.get();
        campaign.setId(campaignId);//Required to force update
        campaign.setDonations(oldCampaign.getDonations());//Rescue list of donations
        Campaign savedCampaign = campaignRepository.save(campaign);
        log.trace("Updated campaign: "+ savedCampaign.toString());
        return  savedCampaign;
    }

    public Campaign deleteCampaign(Long campaignId) {
        log.debug("Delete campaign with Id: "+ campaignId);
        Optional<Campaign> optional = campaignRepository.findById(campaignId);
        if (!optional.isPresent()) throw new CampaignNotFoundException("Campaign with "+campaignId+" not found.");        
        Campaign deletedCampaign = optional.get();
        campaignRepository.deleteById(campaignId);
        log.trace("Deleted campaign: "+ deletedCampaign.toString());
        return deletedCampaign;
    }
}
