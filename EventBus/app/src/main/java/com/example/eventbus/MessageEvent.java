package com.example.eventbus;

public class MessageEvent {
    public String mMessage;

    public MessageEvent(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
