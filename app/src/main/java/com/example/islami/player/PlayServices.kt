package com.example.islami.player

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.islami.R
import com.example.islami.myApplication

class PlayServices: Service() {
    private val binder = MyBinder()
    var mediaPlayer: MediaPlayer?= null
    var name:String = ""


    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    inner class MyBinder(): Binder(){

        fun getServices():PlayServices{
            return this@PlayServices
        }
    }
    override fun onCreate() {
        super.onCreate()
        initializeMediaPlayer()
    }
    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer()
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()

            mediaPlayer?.setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )

            mediaPlayer?.setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
        }
    }



    override fun onDestroy() {
        mediaPlayer?.release()
        super.onDestroy()
        stopMediaPlayer()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val name = intent?.getStringExtra("name")
        val urlToPlay = intent?.getStringExtra("urlToPlay")
        val action = intent?.getStringExtra("action")


        if(name != null && urlToPlay != null){
            startMediaPlayer(urlToPlay,name)
        }
        if(action.equals("pause")){
            pauseMediaPlayer()
        }else if (action.equals("stop")){
            stopMediaPlayer()
        }else if (action.equals("play")) {
            resumeMediaPlayer()  // Added this line to handle play action
        }

        return START_NOT_STICKY
    }
    fun resumeMediaPlayer() {
        mediaPlayer?.start()
        // Update notification
        updateNotification()
    }

    fun startMediaPlayer(urlToPlay: String, name: String) {
        this.name = name

        // Check if mediaPlayer is null or already playing/prepared
        if (mediaPlayer == null || mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            initializeMediaPlayer()
        }

        try {
            mediaPlayer?.setDataSource(urlToPlay)
            mediaPlayer?.prepareAsync()
            mediaPlayer?.setOnPreparedListener {
                it.start()
            }
            // Create notification method
            createNotification(name)

        } catch (e: Exception) {
            Log.e("MediaPlayerError", "Error starting MediaPlayer: ${e.message}")
        }
    }



    fun pauseMediaPlayer() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
            } else {
                it.start()
            }
        }
        // Update notification
//        updateNotification()
    }
    fun pauseMediaPlayer2() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
            }
        }
    }
    fun stopMediaPlayer() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.reset()
        }
        // Update notification instead of creating a new one
        updateNotification()
        stopSelf()
    }

    // This function can be called when your music starts playing
    fun createNotification(name: String) {

        val defaultViews = RemoteViews(packageName,R.layout.notification_layout)
        defaultViews.setTextViewText(R.id.tv_notifi_title,"Islami")
        defaultViews.setTextViewText(R.id.tv_notifi_description,name)
        defaultViews.setImageViewResource(
            R.id.iv_play,
            if (mediaPlayer!!.isPlaying) R.drawable.iv_radio_play else R.drawable.ic_stop)
        defaultViews.setOnClickPendingIntent(R.id.iv_notifi_play,getPlayButtonPendingIntent())
        defaultViews.setOnClickPendingIntent(R.id.iv_notifi_stop,getStopButtonPendingIntent())

        val notification = NotificationCompat.Builder(this, myApplication.RADIO_PLAYER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_small_notification)
            .setCustomContentView(defaultViews)
            .setSound(null)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_MAX) // Adjust as needed
            .setOngoing(true)
            .setDefaults(0)
            .build()

        startForeground(START_FORGROUND_ID,notification)

    }

    val START_FORGROUND_ID = 555

    // Call this function when your music stops playing
    fun updateNotification() {
        val defaultViews = RemoteViews(packageName,R.layout.notification_layout)
        defaultViews.setTextViewText(R.id.tv_notifi_title,"Islami")
        defaultViews.setTextViewText(R.id.tv_notifi_description,name)
        defaultViews.setImageViewResource(
            R.id.iv_play,
            if (mediaPlayer!!.isPlaying) R.drawable.iv_radio_play else R.drawable.ic_stop)
        defaultViews.setOnClickPendingIntent(R.id.iv_notifi_play,getPlayButtonPendingIntent())
        defaultViews.setOnClickPendingIntent(R.id.iv_notifi_stop,getStopButtonPendingIntent())

        val notification = NotificationCompat.Builder(this, myApplication.RADIO_PLAYER_CHANNEL_ID)
            .setContentTitle(name)
            .setSmallIcon(R.drawable.ic_small_notification)
            .setCustomContentView(defaultViews)
            .setSound(null)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_MAX) // Adjust as needed
            .setOngoing(true)
            .setDefaults(0)
            .build()

        startForeground(START_FORGROUND_ID, notification)
        val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        notificationManager.notify(UPDATE_NOTIFY_ID,notification) // Use a unique notification ID
    }
    val UPDATE_NOTIFY_ID = 3000
    val requestCode_play = 1010
    val requestCode_stop = 2000
    val STOP_ACTION = "stop"
    val PLAY_ACTION = "play"
    private fun getPlayButtonPendingIntent(): PendingIntent? {
        val playIntent = Intent(this,PlayServices::class.java)
        playIntent.putExtra("action",PLAY_ACTION)
        val pendingIntent = PendingIntent
            .getService(this,requestCode_play,playIntent, PendingIntent.FLAG_IMMUTABLE)
        return pendingIntent
    }
    private fun getStopButtonPendingIntent(): PendingIntent? {
        val intent = Intent(this,PlayServices::class.java)
        intent.putExtra("action",STOP_ACTION)
        val pendingIntent = PendingIntent
            .getService(this,requestCode_stop,intent,PendingIntent.FLAG_IMMUTABLE)
        return pendingIntent
    }

    fun resetMediaPlayer() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.reset()
        }
    }


}