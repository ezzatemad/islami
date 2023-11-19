package com.example.islami

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splash_Activity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the night mode status from SharedPreferences
        sharedPreferences = getSharedPreferences("nightMode", Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("nightMode", false)



        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // Create an Intent to start the SecondActivity
            val intent = Intent(this, home_activity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

}