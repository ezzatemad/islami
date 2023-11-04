package com.example.islami.Quran

import QuranContentViewModelFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.islami.adapters.sura_content_adapter
import com.example.islami.data.constant_value
import com.example.islami.databinding.ActivityQuranContentBinding

class QuranContentActivity : AppCompatActivity() {
    private lateinit var viewModel: quranContentViewModel
    var sura_name :String? = null
    lateinit var adapter : sura_content_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityQuranContentBinding = ActivityQuranContentBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        sura_name = intent.getStringExtra(constant_value.CONTENT_SURA_NAME)
        val suraPosition = intent.getIntExtra(constant_value.CONTENT_SURA_POSITION, -1)
        binding.tvContentSuraName.text = sura_name
        adapter = sura_content_adapter(null)
        binding.rvContentSura.adapter = adapter

        viewModel = ViewModelProvider(this,QuranContentViewModelFactory(this))[quranContentViewModel::class.java]
        viewModel.loadContent(suraPosition)

        viewModel.getContentData().observe(this, Observer { contentList ->
            adapter.updateData(contentList)
        })

        binding.ivContentBack.setOnClickListener {
            finish()
        }

    }

}