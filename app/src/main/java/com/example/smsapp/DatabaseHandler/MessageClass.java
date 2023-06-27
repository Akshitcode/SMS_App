package com.example.smsapp.DatabaseHandler;

public class MessageClass {

    private int id;
    private String phoneNo;
    private String sentMessage;

    public MessageClass(int id, String phoneNo, String sentMessage) {
        this.phoneNo = phoneNo;
        this.sentMessage = sentMessage;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getSentMessage() {
        return sentMessage;
    }
}
