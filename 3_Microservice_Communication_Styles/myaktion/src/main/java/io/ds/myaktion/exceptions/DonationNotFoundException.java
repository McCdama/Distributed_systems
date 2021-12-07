package io.ds.myaktion.exceptions;

// Will be called if the related transaction was processed, but the donation does not exist in DB anymore! (Bug!)
public class DonationNotFoundException extends RuntimeException {
    public DonationNotFoundException() {
    }

    public DonationNotFoundException(String message) {
        super(message);
    }

    public DonationNotFoundException(Throwable cause) {
        super(cause);
    }

    public DonationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DonationNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
