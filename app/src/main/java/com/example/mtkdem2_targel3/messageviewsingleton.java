package com.example.mtkdem2_targel3;

public class messageviewsingleton {

    private static messageviewsingleton instance;
    private MessageViewModel messageViewModel;

    private messageviewsingleton() {
    }

    public static messageviewsingleton getInstance() {
        if (instance == null) {
            instance = new messageviewsingleton();
        }
        return instance;
    }

    public MessageViewModel getMessageViewModel() {
        return messageViewModel;
    }

    public void setMessageViewModel(MessageViewModel messageViewModel) {
        this.messageViewModel = messageViewModel;
    }
}
