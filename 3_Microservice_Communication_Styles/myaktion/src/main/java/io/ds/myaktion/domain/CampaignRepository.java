package io.ds.myaktion.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query("select SUM(d.amount) FROM Donation d where d.campaign.id =?1")
    double getAmountDonatedSoFar(Long campaignId);
}
