package com.mudrahome.mhlms.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.mudrahome.mhlms.firebase.NotificationHelper;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String customerName = intent.getStringExtra("name");
        Log.d("AlertReceiver", " context " );
        NotificationHelper notificationHelper = new NotificationHelper(context, customerName);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(customerName.hashCode(), nb.build());
    }
}