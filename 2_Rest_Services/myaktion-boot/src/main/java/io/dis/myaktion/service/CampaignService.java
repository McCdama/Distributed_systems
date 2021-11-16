package io.dis.myaktion.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dis.myaktion.domain.Campaign;
import io.dis.myaktion.repository.CampaignRepository;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign addCampaign(Campaign campaign){
        Campaign savedCampaign = campaignRepository.save(campaign); 
        return savedCampaign;
    }

    public List<Campaign> getCampaigns(){
        List<Campaign> campaigns = campaignRepository.findAll();
        return campaigns;
    }
}
