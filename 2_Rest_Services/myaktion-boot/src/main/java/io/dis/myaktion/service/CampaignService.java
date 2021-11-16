package io.dis.myaktion.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import io.dis.myaktion.domain.Campaign;
import io.dis.myaktion.repository.CampaignRepository;

public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign addCampaign(Campaign campaign){
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getCampaigns(){
        List<Campaign> campaigns = campaignRepository.findAll();
        return campaigns;
    }
}
