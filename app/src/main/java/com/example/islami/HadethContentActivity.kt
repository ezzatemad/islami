package com.example.islami

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.islami.data.constant_value
import com.example.islami.databinding.ActivityHadethContentBinding

class HadethContentActivity : AppCompatActivity() {
    var hadeth_name :String? = null
    var hadeth_Content :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHadethContentBinding = ActivityHadethContentBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        hadeth_name = intent.getStringExtra(constant_value.CONTENT_HADETH_TITLE)
        hadeth_Content = intent.getStringExtra(constant_value.CONTENT_HADETH_CONTENT)
        binding.tvContentHadethName.text = hadeth_name
        binding.tvContentHadethContent.text = hadeth_Content

        binding.ivContentHadethBack.setOnClickListener {
            finish()
        }

    }


}