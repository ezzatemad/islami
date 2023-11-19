package com.example.islami.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.islami.Quran.QuranContentActivity
import com.example.islami.R
import com.example.islami.data.Suraslist
import com.example.islami.data.constant_value
import com.example.islami.adapters.quran_adapter
import com.example.islami.data.quran_data
import com.example.islami.databinding.FragmentQuranBinding


class QuranFragment : Fragment() {
    lateinit var viewBinding: FragmentQuranBinding
    lateinit var quran_adapter: quran_adapter
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

        // Retrieve the night mode status from SharedPreferences
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("nightMode", false)


        enableDarkMode(isNightMode)

        viewBinding.ivSwitch.setOnClickListener {
            // Toggle dark mode
            val newNightMode = !isNightMode
            enableDarkMode(newNightMode)

            // Save the night mode status to SharedPreferences
            with(sharedPreferences.edit()) {
                putBoolean("nightMode", newNightMode)
                apply()
            }
        }

        quran_adapter = quran_adapter(Suraslist.mapIndexed{
            index,it -> quran_data(tital =it , order_num = (index+1))
        })
        quran_adapter.onItemClickListener = object: quran_adapter.OnItemClickListener {
            override fun onItemClick(itemView: quran_data) {
                val intent = Intent(requireActivity(), QuranContentActivity::class.java)
                intent.putExtra(constant_value.CONTENT_SURA_NAME,itemView.tital)
                intent.putExtra(constant_value.CONTENT_SURA_POSITION,itemView.order_num)
                startActivity(intent)
            }
        }
        viewBinding.rvQuran.adapter = quran_adapter

    }
     fun updateSwitchUI(isNightMode: Boolean) {
        if (isNightMode) {
            viewBinding.ivSwitch.setImageResource(R.drawable.light_mode)
            viewBinding.ivSwitch.setColorFilter(Color.WHITE)
        } else {
            viewBinding.ivSwitch.setImageResource(R.drawable.nightlight_round)
            viewBinding.ivSwitch.setColorFilter(null)
        }
    }
    private fun enableDarkMode(isNightMode: Boolean) {
        // Set the default night mode
        AppCompatDelegate.setDefaultNightMode(
            if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        // Update the UI based on the current night mode
        updateSwitchUI(isNightMode)
    }
}