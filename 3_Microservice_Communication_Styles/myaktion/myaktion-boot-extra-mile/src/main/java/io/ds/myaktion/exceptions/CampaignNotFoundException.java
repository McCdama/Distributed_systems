package io.ds.myaktion.exceptions;

public class CampaignNotFoundException extends RuntimeException {

    public CampaignNotFoundException() {
    }

    public CampaignNotFoundException(String message) {
        super(message);
    }

    public CampaignNotFoundException(Throwable cause) {
        super(cause);
    }

    public CampaignNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CampaignNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
