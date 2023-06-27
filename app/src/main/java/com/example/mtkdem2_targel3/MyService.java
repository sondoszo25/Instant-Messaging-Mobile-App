package com.example.mtkdem2_targel3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // Extract the data from the message
        Map<String, String> data = remoteMessage.getData();
        String receiver = data.get("reciver");
        String sender = data.get("sender");
        ContactsViewModelSingleton.getInstance().getContactsViewModel().reload();
        if(messageviewsingleton.getInstance().getMessageViewModel() != null)
        {
            messageviewsingleton.getInstance().getMessageViewModel().reload();
        }

    }
}