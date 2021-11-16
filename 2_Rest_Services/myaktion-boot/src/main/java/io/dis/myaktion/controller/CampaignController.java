package io.dis.myaktion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dis.myaktion.domain.Campaign;
import io.dis.myaktion.service.CampaignService;

@RestController
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

     @GetMapping("/campaignes")
     public List<Campaign> getCampaigns(){
         return campaignService.getCampaigns();
     }
}
