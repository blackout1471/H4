package com.example.notificationstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.notificationBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
    }

    /**
     * Adds notification to the system
     */
    private void addNotification() {
        // 1. Get notification manager
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notification_id = 2345;
        String channelId = "channel01";

        // 2. If version is Higher than oreo (sdk 26) android 8.0
        // A notification channel has to be created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            // Notification channel constants
            CharSequence name = "channel";
            String description = "channel001";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            // Create new channel
            NotificationChannel nc = new NotificationChannel(channelId, name, importance);
            nc.setDescription(description);
            nc.enableLights(true);
            nc.enableVibration(true);
            nc.setVibrationPattern(new long[] {100, 200, 300, 400, 500, 400, 300, 400});
            nc.setShowBadge(false);

            // let system create the specified notification channel
            manager.createNotificationChannel(nc);
        }

        // 3. setup notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_nc)
                .setContentTitle("Eksempel")
                .setContentText("Her ligger content text");

        // 4. setup notification intent
        Intent notificationInt = new Intent(this, MainActivity.class);

        // 5. Setup content intent, this intent runs an activity when user interacts with notification.
        PendingIntent contentInt = PendingIntent.getActivity(this, 0, notificationInt,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // 6. Attach pending intent to notification
        builder.setContentIntent(contentInt);

        // 7. Add notification to system service
        manager.notify(notification_id, builder.build());
    }
}