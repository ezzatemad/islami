package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.R
import com.example.islami.adapters.sura_content_adapter.*

class sura_content_adapter(var list :List<String> ?= null): RecyclerView.Adapter<suraviewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): suraviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sura_content_layout,parent,false)
        return suraviewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: suraviewHolder, position: Int) {
        holder.sura_content.text = list?.get(position)
    }
    fun updateData(list: List<String>?)
    {
        this.list= list
        notifyDataSetChanged()
    }

    class suraviewHolder(view :View):ViewHolder(view){

        val sura_content : TextView = view.findViewById(R.id.tv_sura_content)
    }
}