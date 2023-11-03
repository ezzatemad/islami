package com.example.islami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper


class splashactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spalshactivity)


        Handler(Looper.getMainLooper()).postDelayed(object :Runnable{

            override fun run() {
                val intent= Intent(this@splashactivity,home_activity::class.java)
                startActivity(intent)
                finish()
            }
        },2000)
    }
}