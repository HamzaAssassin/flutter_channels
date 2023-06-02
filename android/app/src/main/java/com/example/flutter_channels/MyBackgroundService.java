package com.example.flutter_channels;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBackgroundService extends Service {

    final String CHANNEL_ID = "ServiceID";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService", "Service is running...");
        showNotification("Service", "Background Service Started");
//        new CountDownTimer(60000, 10000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                Log.d("CountDown", "CountDown: " + millisUntilFinished / 1000);
//
//            }
//
//            @Override
//            public void onFinish() {
//                Log.d("CountDown", "onFinish: ");
//            }
//        }.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService", "Service is Stopped");
        showNotification("Service", "Background Service Stopped");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_ID,
                    NotificationManager.IMPORTANCE_HIGH
            );
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }

    void showNotification(String title, String content) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyBackgroundService.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MyBackgroundService.this);

        // Show the notification
        notificationManager.notify(1, builder.build());
    }
}
