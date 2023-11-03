package com.example.islami.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.islami.R

class SebhFragment : Fragment() {
    lateinit var tv_num_tasbeeh:TextView
    lateinit var btn_tasbeeh:Button
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
        return inflater.inflate(R.layout.fragment_sebh, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_num_tasbeeh = view.findViewById(R.id.tv_num_tasbeeh)
        btn_tasbeeh = view.findViewById(R.id.btn_tasbeeh)

        tv_num_tasbeeh.text = "0"
        btn_tasbeeh.text = "سبحان الله"

        btn_tasbeeh.setOnClickListener {
            number++
            tv_num_tasbeeh.text = (number).toString()
            if(number == 33 && btn_tasbeeh.text == "سبحان الله")
            {
                btn_tasbeeh.text = "الحمد لله"
                tv_num_tasbeeh.text = "0"
                number = 0
            }
            else if(number == 33 && btn_tasbeeh.text == "الحمد لله")
            {
                btn_tasbeeh.text = "الله اكبر"
                tv_num_tasbeeh.text = "0"
                number = 0
            }
            else if(number == 33 && btn_tasbeeh.text == "الله اكبر")
            {
                btn_tasbeeh.text = "سبحان الله"
                tv_num_tasbeeh.text = "0"
                number = 0
            }
        }

    }



}