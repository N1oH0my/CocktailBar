package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.study.View.Fragments.HomePageFragment
import com.example.weatherapp.ViewModels.MainViewModel
import androidx.activity.viewModels
import com.example.study.databinding.ActivityMainBinding
import androidx.lifecycle.lifecycleScope


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_place_holder, HomePageFragment.newInstance())
            .commit()
    }

}