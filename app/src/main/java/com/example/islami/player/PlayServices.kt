package com.example.islami.player

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.os.PowerManager
import android.util.Log

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
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

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
        }

        return START_NOT_STICKY
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
            // TODO: Create notification method
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
        // TODO: Update notification
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
             if (it != null && it.isPlaying) {
                 it.stop()
                 it.reset()
             }
//             stopSelf()
//             stopForeground(true)
         }
     }
}