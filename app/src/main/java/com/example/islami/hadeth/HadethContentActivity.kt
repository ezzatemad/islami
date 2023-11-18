package com.example.islami.hadeth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.islami.Quran.quranContentViewModel
import com.example.islami.data.constant_value
import com.example.islami.databinding.ActivityHadethContentBinding

class HadethContentActivity : AppCompatActivity() {

     lateinit var viewModel: hadethContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHadethContentBinding = ActivityHadethContentBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        viewModel = ViewModelProvider(this)[hadethContentViewModel::class.java]
        
        viewModel.hadethName = intent.getStringExtra(constant_value.CONTENT_HADETH_TITLE)
        viewModel.hadethContent = intent.getStringExtra(constant_value.CONTENT_HADETH_CONTENT)
        binding.tvContentHadethName.text = viewModel.hadethName
        binding.tvContentHadethContent.text = viewModel.hadethContent

        binding.ivContentHadethBack.setOnClickListener {
            finish()
        }

    }


}