package io.ds.myaktion.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import io.ds.myaktion.dto.Transaction;
import io.ds.myaktion.services.DonationService;
import io.lettuce.core.RedisCommandTimeoutException;

// Will be registered as callback method of Redis
public class ProcessedTransactionReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessedTransactionReceiver.class);

    @Autowired
    private RedisTemplate<String, Transaction> redisTemplate; // inject Redis template

    @Autowired
    DonationService donationService;

    public void receiveProcessedTransaction(Transaction transaction) {
        LOGGER.info("Received <" + transaction + ">");
        try {
            // Change state of donation
            // donationService.changeDonationState(transaction.getDonationId());
            // LOGGER.info("Changed state of donation to TRANSFERRED. donationId=" + transaction.getDonationId());
            redisTemplate.convertAndSend("transactionProcessed", transaction);
        } catch (RedisCommandTimeoutException e) {
            // LOGGER.error("Donation already deleted.", e);
            // to_do: implement rety mechanis
            LOGGER.info("Could not send transaction <" + transaction + ">");
        }
    }
}
