package com.example.islami.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.islami.adapters.radio_Adapter
import com.example.islami.api.model.QuranResponse
import com.example.islami.api.model.RadiosItem
import com.example.islami.api.services.APIManger
import com.example.islami.databinding.FragmentRadioBinding
import com.example.islami.player.PlayServices
import retrofit2.Call
import retrofit2.Response


class RadioFragment : Fragment() {

    lateinit var viewBinding: FragmentRadioBinding
    var bound = false
    lateinit var services: PlayServices
    val adapter = radio_Adapter()


    // Service connection to interact with PlayServices
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as PlayServices.MyBinder
            services = binder.getServices()
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bind to the PlayServices service when the fragment is created
        val serviceIntent = Intent(requireContext(), PlayServices::class.java)
        requireContext().bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRadioBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onStart() {
        super.onStart()
        startService()
        bindService()
    }

    private fun bindService() {
        val intent = Intent(activity,PlayServices::class.java)
        activity?.bindService(intent,connection,Context.BIND_AUTO_CREATE)
    }

    val connection = object :ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as PlayServices.MyBinder
            services = binder.getServices()
            bound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            bound = false
        }

    }
    private fun startService() {
        val intent = Intent(activity,PlayServices::class.java)
        activity?.startService(intent)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvRadio.adapter = adapter

        adapter.onItemClickPlay = object : radio_Adapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: RadiosItem) {
                Toast.makeText(activity, "Play clicked", Toast.LENGTH_LONG).show()
                startRadioService(item)
            }
        }
        adapter.onItemClickStop = object : radio_Adapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: RadiosItem) {
                Toast.makeText(activity, "Stop clicked", Toast.LENGTH_LONG).show()
                startPlayService()
            }
        }
        getRadioFromAPI()
    }


    private fun startRadioService(item: RadiosItem) {
        if (bound && item.name != null && item.url != null){
            services.startMediaPlayer(urlToPlay = item.url, name = item.name)
        }
    }
    private fun startPlayService() {
        if (bound){
            services.pauseMediaPlayer()
        }

    }
    private fun getRadioFromAPI() {
        APIManger
            .getWebService()
            .getRadio()
            .enqueue(object : retrofit2.Callback<QuranResponse>{
                override fun onResponse(
                    call: Call<QuranResponse>,
                    response: Response<QuranResponse>
                ) {
                    val radioList = response.body()?.radios
                    adapter.notifylistChange(radioList?: listOf())
                }
                override fun onFailure(call: Call<QuranResponse>, t: Throwable) {
                    Toast.makeText(activity,t.localizedMessage?:"error found",Toast.LENGTH_LONG).show()
                }

            })
    }
    override fun onDestroy() {
        super.onDestroy()
        if (bound) {
            requireContext().unbindService(serviceConnection)
            bound = false
        }
    }

}