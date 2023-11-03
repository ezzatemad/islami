package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.R
import com.example.islami.data.hadeth_data

class hadeth_adapter(val list:List<hadeth_data>): Adapter<hadeth_adapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hadeth_layout,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size ?: 0
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = list.get(position)
        holder.hadeth_name.text = data.hadethTital
        holder.hadeth_name.setOnClickListener {
            onItemClickListener?.onItemClick(data)
        }
    }

    var onItemClickListener : OnItemClickListener?= null
    interface OnItemClickListener {
        fun onItemClick(itemView: hadeth_data)
    }

class viewHolder(view: View):ViewHolder(view){
    val hadeth_name :TextView = view.findViewById(R.id.tv_hadeth_name)
}
}
