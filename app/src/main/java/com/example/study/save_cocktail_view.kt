package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.study.databinding.ActivityCocktailViewBinding
import com.example.study.databinding.ActivitySaveCocktailViewBinding

class save_cocktail_view : AppCompatActivity() {
    private lateinit var binding: ActivitySaveCocktailViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_cocktail_view)
        binding = ActivitySaveCocktailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageView = binding.idTitleItemImg
        Glide.with(this)
            .load(R.drawable.place_holder)
            .into(imageView)
    }
}