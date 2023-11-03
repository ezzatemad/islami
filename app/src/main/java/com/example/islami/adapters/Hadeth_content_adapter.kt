package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.R
import com.example.islami.adapters.sura_content_adapter.*

class Hadeth_content_adapter(var list :List<String> ?= null): RecyclerView.Adapter<Hadeth_content_adapter.hadethviewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hadethviewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hadeth_content_layout,parent,false)
        return hadethviewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: hadethviewHolder, position: Int) {
        holder.hadeth_content.text = list?.get(position)
    }
    fun updateData(list: List<String>?)
    {
        this.list= list
        notifyDataSetChanged()
    }

    class hadethviewHolder(view :View):ViewHolder(view){

        val hadeth_content : TextView = view.findViewById(R.id.tv_content_hadethName)
    }
}