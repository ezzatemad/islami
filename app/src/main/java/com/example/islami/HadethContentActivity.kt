package com.example.islami

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.adapters.Hadeth_content_adapter
import com.example.islami.data.constant_value

class HadethContentActivity : AppCompatActivity() {
    var hadeth_name :String? = null
    var hadeth_Content :String? = null
    lateinit var hadeth_text : TextView
    lateinit var tv_hadeth_content: TextView
    lateinit var iv_back: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadeth_content)
        iv_back = findViewById(R.id.iv_content_hadeth_back)
        hadeth_text = findViewById(R.id.tv_content_hadethName)
        tv_hadeth_content = findViewById(R.id.tv_content_hadethContent)
        hadeth_name = intent.getStringExtra(constant_value.CONTENT_HADETH_TITLE)
        hadeth_Content = intent.getStringExtra(constant_value.CONTENT_HADETH_CONTENT)
        hadeth_text.text = hadeth_name
        tv_hadeth_content.text = hadeth_Content

        iv_back.setOnClickListener {
            finish()
        }

    }


}