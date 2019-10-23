package com.egswebapp.egsweb.util;


import com.egswebapp.egsweb.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

public class EmailSendUtil {

    private Session session;

    private Properties prop;

    private static final EmailSendUtil instance = new EmailSendUtil();
    private static final SampleVelocity sampleVelocity = new SampleVelocity();

    private EmailSendUtil() {
        InputStream input = EmailSendUtil.class.getClassLoader().getResourceAsStream("mail-config.properties");
        {
            prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find mail-config.properties");
            } else {
                try {
                    prop.load(input);
                    session = Session.getDefaultInstance(prop, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    prop.getProperty("mail.username"),
                                    prop.getProperty("mail.password"));
                        }
                    });
                } catch (Exception ex) {
                    throw new RuntimeException("Unable to send email", ex);
                }
            }
        }
    }

    public static EmailSendUtil getInstance() {
        return instance;
    }


    public void sendEmail( final User user, final Email email) {

        try {
            BodyPart messageBodyPart = new MimeBodyPart();
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipientAddress()));
            message.setSubject(email.getSubject());

            StringWriter out = sampleVelocity.configVelocity(email.getBody(), user);

            messageBodyPart.setContent(out.toString(), "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart, "text/html");

            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
