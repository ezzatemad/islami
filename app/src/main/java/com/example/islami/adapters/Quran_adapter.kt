package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.data.quran_data
import com.example.islami.databinding.FragmentQuranBinding
import com.example.islami.databinding.SuraLayoutBinding


class quran_adapter(val list :List<quran_data>):
    RecyclerView.Adapter<quran_adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SuraLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }
    var onItemClickListener : OnItemClickListener?= null
    interface OnItemClickListener {
        fun onItemClick(itemView: quran_data)
    }

    inner class ViewHolder(private val binding: SuraLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: quran_data) {
            binding.suraName.text = data.tital
            binding.suraOrder.text = data.order_num.toString()
            binding.suraName.setOnClickListener {
                onItemClickListener?.onItemClick(data)
            }
        }
    }
}