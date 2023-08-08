package com.example.study

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.study.Models.Cocktail
import com.example.study.databinding.ActivityCocktailViewBinding
import com.example.study.databinding.FragmentCocktailsListBinding

class cocktail_view_activity : AppCompatActivity() {
    private lateinit var binding: ActivityCocktailViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_view)
        binding = ActivityCocktailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val recipe = intent.getStringExtra("recipe")

        binding.idTitleText.text = title
        binding.idDesccriptionText.text = description
        binding.idRecipeText.text = recipe

        val imageView = binding.idTitleItemImg
        val image = intent.getStringExtra("image")
        if (image != null) {
            val imageUri = Uri.parse(image)
            Glide.with(this)
                .load(imageUri)
                .into(imageView)
        }
        else{
            Glide.with(this)
                .load(R.drawable.place_holder)
                .into(imageView)
        }
    }

}