package com.example.islami.fragment

import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islami.hadeth.HadethContentActivity
import com.example.islami.adapters.hadeth_adapter
import com.example.islami.data.constant_value
import com.example.islami.data.hadeth_data
import com.example.islami.databinding.FragmentHadethBinding

class HadethFragment : Fragment() {
    lateinit var viewBinding: FragmentHadethBinding
    lateinit var hadeth_adapter :hadeth_adapter
    lateinit var list :List<hadeth_data>
    lateinit var titleHadeth :String
    lateinit var contentHadeth :String
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
        viewBinding = FragmentHadethBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readFileHadeth()
        hadeth_adapter = hadeth_adapter(hadethList)

        hadeth_adapter.onItemClickListener = object: hadeth_adapter.OnItemClickListener {
            override fun onItemClick(itemView: hadeth_data) {
                val intent = Intent(requireActivity(), HadethContentActivity::class.java)
                intent.putExtra(constant_value.CONTENT_HADETH_TITLE,itemView.hadethTital)
                intent.putExtra(constant_value.CONTENT_HADETH_CONTENT,itemView.hadethContent)
                startActivity(intent)
            }
        }
        viewBinding.rvHadeth.adapter = hadeth_adapter
    }
    val hadethList = mutableListOf<hadeth_data>()
    fun readFileHadeth()
    {
        val hadethContent = activity?.assets?.open("ahadeth .txt")?.bufferedReader().use { it?.readText() }
        val singleHadeth = hadethContent?.trim()?.split("#")
        singleHadeth?.forEach {
            val lines = it.trim().split("\n")
            titleHadeth = lines[0]
            contentHadeth = lines.joinToString("\n")
            val hadeth = hadeth_data(titleHadeth,contentHadeth)
            hadethList.add(hadeth)
        }

    }
}