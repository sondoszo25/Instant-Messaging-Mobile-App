package com.example.mtkdem2_targel3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
        if(ContactsViewModelSingleton.getInstance().getContactsViewModel() != null) {
            ContactsViewModelSingleton.getInstance().getContactsViewModel().reload();
        }
        if (messageviewsingleton.getInstance().getMessageViewModel() != null) {
            messageviewsingleton.getInstance().getMessageViewModel().reload();
        }
        if (receiver != null && receiver.equals(usernamesingelton.getInstance().getUsername())) {
            createNotificationChannel();
            showNotification(sender);
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            String channelId = "2";

            NotificationChannel channel = new NotificationChannel("2", "3", importance);
            channel.setDescription("Msg get");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    private void showNotification(String sender) {
        Context context = getApplicationContext();
        String channelId = "2";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("Received Msg")
                .setContentText("You received a message from " + sender)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notifiction);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(2, builder.build());
    }
}
