package com.example.appmarvel.view.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appmarvel.R
import com.example.appmarvel.databinding.ActivityMainBinding
import com.example.appmarvel.view.ui.fragment.FavoriteFragment
import com.example.appmarvel.view.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}