package com.example.islami.api

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.islami.player.PlayServices

class broadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "PLAY_ACTION" -> {
                // Handle play action
                sendCommandToService(context, "play")
            }
            "STOP_ACTION" -> {
                // Handle stop action
                sendCommandToService(context, "stop")
            }
        }
    }

    private fun sendCommandToService(context: Context, action: String) {
        val serviceIntent = Intent(context, PlayServices::class.java)
        serviceIntent.putExtra("action", action)
        context.startService(serviceIntent)
    }
}
