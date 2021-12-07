package io.ds.myaktion.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.ds.myaktion.bank.dto.Transaction;

public class TransactionReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionReceiver.class);

    public void receiveTransaction(Transaction transaction) {
        LOGGER.info("Received <" + transaction + ">");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Transaction processed <" + transaction + ">");
    }

}
