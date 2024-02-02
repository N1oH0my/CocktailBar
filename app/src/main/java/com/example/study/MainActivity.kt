package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.study.presentation.Fragments.HomePageFragment


class MainActivity : AppCompatActivity() {
    private val activities = ArrayList<AppCompatActivity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finishAllActivities()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_place_holder, HomePageFragment.newInstance())
            .commit()
    }
    private fun finishAllActivities() {
        for (activity in activities) {
            activity.finish()
        }
        activities.clear()
    }
}