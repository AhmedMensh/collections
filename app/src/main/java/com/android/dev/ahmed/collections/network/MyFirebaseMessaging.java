package com.android.dev.ahmed.collections.network;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.ui.activties.register.RegisterPresenter;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService {


    private static final String TAG = "MyFirebaseMessaging";
    private static final String CHANNEL_ID = "channel id";
    private static final int NOTIFICATION_ID = 011;

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e(TAG, "onMessageReceived: "+remoteMessage.getData());
        Log.e(TAG, "########################################################## " );
        Log.e(TAG, "onMessageReceived: "+remoteMessage.getFrom());
        Log.e(TAG, "########################################################## " );
        Log.e(TAG, "onMessageReceived: "+remoteMessage.getMessageId());
        Log.e(TAG, "########################################################## " );
        Log.e(TAG, "onMessageReceived: "+remoteMessage.getMessageType());
        Log.e(TAG, "########################################################## " );
        Log.e(TAG, "onMessageReceived: "+remoteMessage.getNotification().getTitle());
        Log.e(TAG, "########################################################## " );
        Log.e(TAG, "onMessageReceived: "+remoteMessage.getNotification().getBody());
        Log.e(TAG, "########################################################## " );
        Log.e(TAG, "onMessageReceived: "+remoteMessage.getOriginalPriority());
        buildNotification("title", "body", "", "");
    }

    private void buildNotification(String messageTitle, String messageBody, String value, String click_action) {
        Intent intent = new Intent(click_action);
        intent.putExtra("data", value);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.drawable.ic_camera)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name =
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }
}
