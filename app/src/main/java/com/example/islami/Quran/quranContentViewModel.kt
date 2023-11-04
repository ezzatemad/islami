package com.example.islami.Quran

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class quranContentViewModel(private val context: Context): ViewModel() {
    private val contentData = MutableLiveData<List<String>>()
    fun getContentData(): LiveData<List<String>> {
        return contentData
    }

    fun loadContent(suraPosition: Int) {
        try {
            // Load content from the file and update contentData
            val content =
                context.assets.open("$suraPosition.txt").bufferedReader().use { it.readText() }
            contentData.postValue(content.trim().split("\n"))
        }catch (ex: Exception){
            throw ex
        }
    }
}