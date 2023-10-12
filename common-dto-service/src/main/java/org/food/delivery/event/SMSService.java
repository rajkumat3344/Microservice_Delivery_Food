package org.food.delivery.event;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class SMSService {


    private String accountSid = "AC198e100db2c8434b893f8edb07f792a2";


    private String authToken = "99712bcf20cfad31e26189b77c8e7dc3";


    private String twilioPhoneNumber = "+19192835743";
    private static final Logger logger = LoggerFactory.getLogger(SMSService.class);

    public void sendSMS(String recipientPhoneNumber, String message) {
        Twilio.init(accountSid, authToken);
        logger.info("Sending SMS to {}: {}", recipientPhoneNumber, message);

        Message.creator(
                new PhoneNumber(recipientPhoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                message
        ).create();
    }
}

