package com.example.islami.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.islami.R
import com.example.islami.databinding.FragmentSebhBinding

class SebhFragment : Fragment() {
    lateinit var viewBinding: FragmentSebhBinding
    var number = 0
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
        viewBinding = FragmentSebhBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.tvNumTasbeeh.text = "0"
        viewBinding.btnTasbeeh.text = "سبحان الله"

        viewBinding.btnTasbeeh.setOnClickListener {
            viewBinding.ivSebhaBody.rotation = viewBinding.ivSebhaBody.rotation+5
            number++
            viewBinding.tvNumTasbeeh.text = (number).toString()
            if(number == 33 && viewBinding.btnTasbeeh.text == "سبحان الله")
            {
                viewBinding.btnTasbeeh.text = "الحمد لله"
                viewBinding.tvNumTasbeeh.text = "0"
                number = 0
            }
            else if(number == 33 && viewBinding.btnTasbeeh.text == "الحمد لله")
            {
                viewBinding.btnTasbeeh.text = "الله اكبر"
                viewBinding.tvNumTasbeeh.text = "0"
                number = 0
            }
            else if(number == 33 && viewBinding.btnTasbeeh.text == "الله اكبر")
            {
                viewBinding.btnTasbeeh.text = "سبحان الله"
                viewBinding.tvNumTasbeeh.text = "0"
                number = 0
            }
        }

    }



}