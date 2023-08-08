package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.study.Fragments.HomePageFragment


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