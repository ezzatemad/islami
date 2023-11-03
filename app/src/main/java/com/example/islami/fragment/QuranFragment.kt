package com.example.islami.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.QuranContentActivity
import com.example.islami.R
import com.example.islami.data.Suraslist
import com.example.islami.data.constant_value
import com.example.islami.adapters.quran_adapter
import com.example.islami.data.quran_data
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuranFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var quran_adapter: quran_adapter
    lateinit var btn_switch : Button
    lateinit var iv_switch : ImageView
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
        return inflater.inflate(R.layout.fragment_quran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_quran)
        iv_switch = view.findViewById(R.id.iv_switch)
        if (isNightMode) {
            iv_switch.setImageResource(R.drawable.light_mode)
            iv_switch.setColorFilter(Color.WHITE)
        } else {
            iv_switch.setImageResource(R.drawable.nightlight_round)
            iv_switch.setColorFilter(null)
        }
        iv_switch.setOnClickListener {

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
        recyclerView.adapter = quran_adapter

    }

}