package io.dis.myaktion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.dis.myaktion.domain.Account;
import io.dis.myaktion.domain.Campaign;
import io.dis.myaktion.domain.Donation;
import io.dis.myaktion.domain.Status;
import io.dis.myaktion.service.CampaignService;
import io.dis.myaktion.service.DonationService;

@SpringBootApplication
public class MyaktionBootApplication {

	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private DonationService donationService;
	public static void main(String[] args) {
		SpringApplication.run(MyaktionBootApplication.class, args);
	}

	// Command-Line RunnerBean
	@Bean
	CommandLineRunner init(){
		return (args) -> {
			Account account = new Account();
			account.setIban("DE4112444312312345");
			account.setName("Mohed Rah");
			account.setNameOfBank("Deutsche Bank");

			Account account1 = new Account();
			account1.setIban("DE4112444312312345");
			account1.setName("TEST");
			account1.setNameOfBank("Sparkasse");

			Campaign campaign1 = new Campaign("Save Planet", 1.0,1.1);
			campaign1.setAccount(account1);

			campaignService.addCampaign(campaign1);
			// Todo: Logger
			List<Campaign> campaigns = campaignService.getCampaigns();

			// Todo: Logger
			System.out.println(campaigns);

			Donation donation= new Donation(12, true, "Mohed Rah", Status.IN_PROCESS);
			donation.setAccount(account1);

			donationService.addDonation(donation, campaigns.get(0).getId());
		
		} ;
	}

}
