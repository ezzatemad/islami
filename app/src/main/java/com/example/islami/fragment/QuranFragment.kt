package com.example.islami.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.QuranContentActivity
import com.example.islami.R
import com.example.islami.data.Suraslist
import com.example.islami.data.constant_value
import com.example.islami.adapters.quran_adapter
import com.example.islami.data.quran_data
import com.example.islami.databinding.FragmentQuranBinding


class QuranFragment : Fragment() {
    lateinit var viewBinding: FragmentQuranBinding
    lateinit var quran_adapter: quran_adapter
    private var isNightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    private lateinit var sharedPreferences: SharedPreferences
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding =  FragmentQuranBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isNightMode) {
            viewBinding.ivSwitch.setImageResource(R.drawable.light_mode)
            viewBinding.ivSwitch.setColorFilter(Color.WHITE)
        } else {
            viewBinding.ivSwitch.setImageResource(R.drawable.nightlight_round)
            viewBinding.ivSwitch.setColorFilter(null)
        }
            viewBinding.ivSwitch.setOnClickListener {

            if (!isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }
        quran_adapter = quran_adapter(Suraslist.mapIndexed{
            index,it -> quran_data(tital =it , order_num = (index+1))
        })
        quran_adapter.onItemClickListener = object: quran_adapter.OnItemClickListener {
            override fun onItemClick(itemView: quran_data) {
                val intent = Intent(requireActivity(),QuranContentActivity::class.java)
                intent.putExtra(constant_value.CONTENT_SURA_NAME,itemView.tital)
                intent.putExtra(constant_value.CONTENT_SURA_POSITION,itemView.order_num)
                startActivity(intent)
            }
        }
        viewBinding.rvQuran.adapter = quran_adapter

    }

}