package com.egswebapp.egsweb.util;

import com.egswebapp.egsweb.model.User;
import org.springframework.stereotype.Component;

@Component
public class CreateMessage {

    public Email createMassage(final String sendTo, final String subject, final String text) {
        final Email email = new Email();
        email.setRecipientAddress(sendTo);
        email.setSubject(subject);
        email.setBody(text);
        return email;
    }
}