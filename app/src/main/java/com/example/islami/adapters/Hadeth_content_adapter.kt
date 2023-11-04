package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.databinding.HadethContentLayoutBinding

class HadethContentAdapter(var list: List<String>? = null) :
    RecyclerView.Adapter<HadethContentAdapter.HadethViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HadethContentLayoutBinding.inflate(inflater, parent, false)
        return HadethViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: HadethViewHolder, position: Int) {
        val data = list?.get(position)
        holder.bind(data)
    }

    fun updateData(list: List<String>?) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class HadethViewHolder(private val binding: HadethContentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String?) {
            binding.tvHadethContent.text = data
        }
    }
}
