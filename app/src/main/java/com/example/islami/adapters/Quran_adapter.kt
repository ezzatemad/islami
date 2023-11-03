package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.R
import com.example.islami.data.quran_data

class quran_adapter(val list :List<quran_data>): Adapter<quran_adapter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sura_layout,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = list.get(position)
        holder.sura_name.text = data.tital
        holder.sura_order.text = data.order_num.toString()
        holder.sura_name.setOnClickListener {
            onItemClickListener?.onItemClick(data)
        }
    }
    var onItemClickListener : OnItemClickListener?= null
    interface OnItemClickListener {
        fun onItemClick(itemView: quran_data)
    }

    class viewHolder(val item:View):ViewHolder(item){

        val sura_name:TextView = item.findViewById(R.id.sura_name)
        val sura_order:TextView = item.findViewById(R.id.sura_order)
    }
}