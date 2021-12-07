package io.ds.myaktion.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.ds.myaktion.exceptions.DonationNotFoundException;
import io.ds.myaktion.services.DonationService;

// Will be registered as callback method of Redis
public class ProcessedTransactionReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessedTransactionReceiver.class);
    @Autowired
    DonationService donationService;

    public void receiveProcessedTransaction(Transaction transaction) {
        LOGGER.info("Received <" + transaction + ">");
        try {
            // Change state of donation
            donationService.changeDonationState(transaction.getDonationId());
            LOGGER.info("Changed state of donation to TRANSFERRED. donationId=" + transaction.getDonationId());
        } catch (DonationNotFoundException e) {
            LOGGER.error("Donation already deleted.", e);
        }
    }
}
