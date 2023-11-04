package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.islami.data.hadeth_data
import com.example.islami.databinding.HadethLayoutBinding

class hadeth_adapter(val list:List<hadeth_data>): Adapter<hadeth_adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.hadeth_layout,parent,false)
//        return viewHolder(view)
        val inflater = LayoutInflater.from(parent.context)
        val binding = HadethLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    var onItemClickListener : OnItemClickListener?= null
    interface OnItemClickListener {
        fun onItemClick(itemView: hadeth_data)
    }

//class viewHolder(view: View):ViewHolder(view){
//    val hadeth_name :TextView = view.findViewById(R.id.tv_hadeth_name)
//}
inner class ViewHolder(private val binding: HadethLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: hadeth_data) {
        binding.tvHadethName.text = data.hadethTital
        binding.tvHadethName.setOnClickListener {
            onItemClickListener?.onItemClick(data)
            }
        }
    }
}
