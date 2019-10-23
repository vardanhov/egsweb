package com.egswebapp.egsweb.util;

public class Email {

    private  String recipientAddress;
    private  String subject;
    private  String body;

    public Email() {
    }

    public Email(String recipientAddress, String subject, String body) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.body = body;
    }


    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Email{" +
                "recipientAddress='" + recipientAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}