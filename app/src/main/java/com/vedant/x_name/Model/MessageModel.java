package com.vedant.x_name.Model;

public class MessageModel {

    String UID, Message;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public int setTimestamp(long timestamp) {
        Timestamp = timestamp;
        return (int) timestamp;
    }

    long Timestamp;

    public MessageModel(String uid, String message, long timestamp) {
        UID = uid;
        Message = message;
        Timestamp = timestamp;
    }

    public MessageModel(String uid, String message) {
        UID = uid;
        Message = message;
    }

    public MessageModel() {
    }

}
