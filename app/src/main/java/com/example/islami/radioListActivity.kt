package com.example.islami

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.islami.adapters.radio_Adapter
import com.example.islami.adapters.radio_vertical_Adapter
import com.example.islami.api.model.QuranResponse
import com.example.islami.api.model.RadiosItem
import com.example.islami.api.services.APIManger
import com.example.islami.databinding.ActivityHomeBinding
import com.example.islami.databinding.ActivityRadioListBinding
import com.example.islami.databinding.FragmentRadioBinding
import com.example.islami.player.PlayServices
import retrofit2.Call
import retrofit2.Response

class radioListActivity : AppCompatActivity() {
    val adapter = radio_vertical_Adapter()
    lateinit var viewBinding: ActivityRadioListBinding
    var bound = false
    lateinit var services: PlayServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRadioListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.rvVerticalRadio.adapter = adapter

        adapter.onItemClickPlay = object : radio_vertical_Adapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: RadiosItem) {
                Toast.makeText(this@radioListActivity, "Play clicked", Toast.LENGTH_LONG).show()
                startRadioService(item)
            }
        }
        adapter.onItemClickStop = object : radio_vertical_Adapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: RadiosItem) {
                Toast.makeText(this@radioListActivity, "Stop clicked", Toast.LENGTH_LONG).show()
                startPlayService()
            }
        }
        getRadioFromAPI()
    }

    override fun onStart() {
        super.onStart()
        startService()
        bindService()
    }

    private fun bindService() {
        val intent = Intent(this,PlayServices::class.java)
        this.bindService(intent,connection, Context.BIND_AUTO_CREATE)
    }

    val connection = object : ServiceConnection {
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
        val intent = Intent(this,PlayServices::class.java)
        this.startService(intent)
    }
    private fun startRadioService(item: RadiosItem) {
        if (bound && item.name != null && item.url != null){
            services.resetMediaPlayer()
            services.startMediaPlayer(urlToPlay = item.url, name = item.name)
        }
    }
    private fun startPlayService() {
        if (bound){
            services.resetMediaPlayer()
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
                    Toast.makeText(this@radioListActivity,t.localizedMessage?:"error found",Toast.LENGTH_LONG).show()
                }

            })
    }


}