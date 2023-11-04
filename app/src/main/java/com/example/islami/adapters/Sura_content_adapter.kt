package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.R
import com.example.islami.adapters.sura_content_adapter.*
import com.example.islami.data.quran_data
import com.example.islami.databinding.SuraContentLayoutBinding
import com.example.islami.databinding.SuraLayoutBinding

class sura_content_adapter(var list :List<String> ?= null): RecyclerView.Adapter<suraViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): suraViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SuraContentLayoutBinding.inflate(inflater,parent,false)
        return suraViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: suraViewHolder, position: Int) {
//        holder.sura_content.text = list?.get(position)
        val data = list?.get(position)
        holder.bind(data)
    }
    fun updateData(list: List<String>?)
    {
        this.list= list
        notifyDataSetChanged()
    }

    inner class suraViewHolder(private val binding: SuraContentLayoutBinding): RecyclerView.ViewHolder(binding.root){

//        val sura_content : TextView = view.findViewById(R.id.tv_sura_content)
        fun bind(data: String?) {
            binding.tvSuraContent.text = data
        }
    }
}