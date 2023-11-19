package com.example.islami

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.islami.databinding.ActivityHomeBinding
import com.example.islami.fragment.HadethFragment
import com.example.islami.fragment.QuranFragment
import com.example.islami.fragment.RadioFragment
import com.example.islami.fragment.SebhFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class home_activity : AppCompatActivity() {
  lateinit var bottomNavigation : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigation = binding.bottomNavigation
        pushFragment(QuranFragment())
        initView()

}
    private fun initView()
    {
        bottomNavigation.setOnItemSelectedListener{
            if(it.itemId == R.id.mu_quran)
            {
                pushFragment(QuranFragment())
            }
            else if(it.itemId == R.id.mu_hadeth)
            {
                pushFragment(HadethFragment())
            }
            else if(it.itemId == R.id.mu_sebah)
            {
                pushFragment(SebhFragment())
            }
            else if(it.itemId == R.id.mu_radio)
            {
                pushFragment(RadioFragment())
            }
            return@setOnItemSelectedListener true
        }
    }


    fun pushFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()
    }
}