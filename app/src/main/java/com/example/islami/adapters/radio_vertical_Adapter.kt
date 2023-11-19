package com.example.islami.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.islami.api.model.RadiosItem
import com.example.islami.databinding.RadioItemBinding
import com.example.islami.databinding.RadioItemVerticalBinding

class radio_vertical_Adapter: Adapter<radio_vertical_Adapter.ViewHolder>() {

    var radio_list = listOf<RadiosItem>()
    lateinit var viewBinding: RadioItemVerticalBinding

    var onItemClickPlay: OnItemClickListener? =null
    var onItemClickStop: OnItemClickListener? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        viewBinding = RadioItemVerticalBinding.inflate(inflater,parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = radio_list[position]
        holder.viewBinding.tvVerticalRadio.text = item.name
        onItemClickPlay.let {
            holder.viewBinding.ivVerticalPlay.setOnClickListener {
                onItemClickPlay?.onItemClick(position, radio_list[position])
            }
        }
        onItemClickStop.let {
            holder.viewBinding.ivVerticalStop.setOnClickListener {
                onItemClickStop?.onItemClick(position, radio_list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return radio_list.size
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int,item: RadiosItem)
    }

    fun notifylistChange(data:List<RadiosItem>){
        this.radio_list = data
        notifyDataSetChanged()
    }


    inner class ViewHolder(var viewBinding: RadioItemVerticalBinding): RecyclerView.ViewHolder(viewBinding.root){

    }
}