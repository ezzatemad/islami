package com.example.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.adapters.sura_content_adapter
import com.example.islami.data.constant_value

class QuranContentActivity : AppCompatActivity() {
    var sura_name :String? = null
    var sura_position :Int? = null
    lateinit var sura_text :TextView
    lateinit var iv_back :ImageView
    lateinit var adapter : sura_content_adapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_content)
        iv_back = findViewById(R.id.iv_content_back)
        sura_text = findViewById(R.id.tv_content_suraName)
        recyclerView = findViewById(R.id.rv_content_sura)
        sura_name = intent.getStringExtra(constant_value.CONTENT_SURA_NAME)
        sura_position = intent.getIntExtra(constant_value.CONTENT_SURA_POSITION,-1)
        sura_text.text = sura_name
        adapter = sura_content_adapter(null)
        recyclerView.adapter = adapter
        readFileText2()
        iv_back.setOnClickListener {
            finish()
        }

    }

    fun readFileText2() {
        val content_list = assets.open("$sura_position.txt").bufferedReader().use { it.readText() }
        adapter.updateData(content_list.trim().split("\n"))
    }
}