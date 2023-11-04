package com.example.islami

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.adapters.sura_content_adapter
import com.example.islami.data.constant_value
import com.example.islami.databinding.ActivityQuranContentBinding

class QuranContentActivity : AppCompatActivity() {
    var sura_name :String? = null
    var sura_position :Int? = null
    lateinit var adapter : sura_content_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityQuranContentBinding = ActivityQuranContentBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        sura_name = intent.getStringExtra(constant_value.CONTENT_SURA_NAME)
        sura_position = intent.getIntExtra(constant_value.CONTENT_SURA_POSITION,-1)
        binding.tvContentSuraName.text = sura_name
        adapter = sura_content_adapter(null)
        binding.rvContentSura.adapter = adapter
        readFileText2()
        binding.ivContentBack.setOnClickListener {
            finish()
        }

    }

    fun readFileText2() {
        val content_list = assets.open("$sura_position.txt").bufferedReader().use { it.readText() }
        adapter.updateData(content_list.trim().split("\n"))
    }
}