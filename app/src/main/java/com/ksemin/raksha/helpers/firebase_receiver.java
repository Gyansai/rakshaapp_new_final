package com.ksemin.raksha.helpers;

import android.app.Notification;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ksemin.raksha.R;

import br.com.goncalves.pugnotification.notification.PugNotification;

public class firebase_receiver extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            String s = remoteMessage.getData().get("score");
            Log.d(TAG, "Message data payload:"+s );
            PugNotification.with(this)
               .load()
                .title("Raksha")
               .message(s)
                    .bigTextStyle(" Raksha")
                .ongoing(false)
                .smallIcon(R.mipmap.applogo)
                .largeIcon(R.mipmap.applogo)
                .flags(Notification.DEFAULT_ALL)
               .simple()
                .build();

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                ////scheduleJob();
            } else {
                // Handle message within 10 seconds
                ////handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

}
