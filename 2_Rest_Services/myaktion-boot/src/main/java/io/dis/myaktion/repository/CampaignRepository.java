package io.dis.myaktion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.dis.myaktion.domain.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    
}
