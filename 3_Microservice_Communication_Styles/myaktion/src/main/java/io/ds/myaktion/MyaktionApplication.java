package io.ds.myaktion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import io.ds.myaktion.domain.Account;
import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.dto.Transaction;
import io.ds.myaktion.services.CampaignService;
import io.ds.myaktion.services.DonationService;

@SpringBootApplication
public class MyaktionApplication {

	@Autowired
	private CampaignService campaignService;
	@Autowired
	private DonationService donationService;

	private Logger log = LoggerFactory.getLogger(MyaktionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyaktionApplication.class, args);
	}

	@Bean
	CommandLineRunner initLoggerBean() {
		return (args) -> {
			log.info("Adding Sample data to DB");
			Account account1 = new Account();
			account1.setName("Jogi Löw");
			account1.setNameOfBank("KSK Freiburg");
			account1.setIban("DE4112312312312345");

			Account account2 = new Account();
			account2.setName("Hansi Flick");
			account2.setNameOfBank("KSK München");
			account2.setIban("DE4112444312312345");

			Account account3 = new Account();
			account3.setName("Berti Vogts");
			account3.setNameOfBank("VoBa Gladbach");
			account3.setIban("DE4112444312314442");

			Campaign campaign1 = new Campaign();
			campaign1.setName("Trikots A-Jugend");
			campaign1.setTargetAmount(1000d);
			campaign1.setDonationMinimum(1d);
			campaign1.setAccount(account1);

			Donation donation1 = new Donation();
			donation1.setAccount(account3);
			donation1.setAmount(199d);
			donation1.setDonorName("Berti Vogts");
			donation1.setReceiptRequested(false);
			donation1.setStatus(Donation.Status.TRANSFERRED);
			donation1.setCampaign(campaign1);
			campaign1.addDonation(donation1);

			// Write campaign to DB
			log.debug("Add campaign to DB");
			Campaign savedCampaign = campaignService.addCampaign(campaign1);

			// Add another donation to campaign
			Donation donation2 = new Donation();
			donation2.setAccount(account2);
			donation2.setAmount(200d);
			donation2.setDonorName("Hansi Flick");
			donation2.setReceiptRequested(true);
			donation2.setStatus(Donation.Status.IN_PROCESS);

			log.debug("Add donation of campaign to DB");
			donationService.addDonation(donation2, savedCampaign.getId());

			// Read data from db and print to screen
			List<Campaign> campaigns = campaignService.getCampaigns();
			log.debug("Read all campaigns again");
			log.trace(campaigns.toString());
		};
	}

	@Bean
	RedisTemplate<String, Transaction> template(RedisConnectionFactory connectionFactory,
			Jackson2JsonRedisSerializer<Transaction> serializer) {
		RedisTemplate<String, Transaction> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setDefaultSerializer(serializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public Jackson2JsonRedisSerializer<Transaction> jackson2JsonRedisSerializer() {
		return new Jackson2JsonRedisSerializer<>(Transaction.class);
	}
}
