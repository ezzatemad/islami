package com.example.islami

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class myApplication: Application() {

    companion object{
        val RADIO_PLAYER_CHANNEL_ID = "radio_channel_id"
    }
    override fun onCreate() {

        super.onCreate()
        // Add this code where you initialize your app, e.g., in onCreate() of your Application class
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.radio_channel_name)
            val desc = getString(R.string.radio_channel_des)
            val impotance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                RADIO_PLAYER_CHANNEL_ID,
                name,
                impotance).apply {
                    description = desc
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

    }
}