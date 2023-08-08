package com.example.study.View

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.study.Models.Cocktail
import com.example.study.R
import com.example.study.databinding.ActivityCocktailViewBinding
import com.example.weatherapp.ViewModels.MainViewModel
import java.text.FieldPosition


class cocktail_view_activity : AppCompatActivity() {
    private lateinit var binding: ActivityCocktailViewBinding
    private lateinit var cocktail:Cocktail
    private val cur_data: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_view)
        binding = ActivityCocktailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Init()
    }
    private fun Init() = with(binding) {
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val recipe = intent.getStringExtra("recipe")
        position = intent.getIntExtra("position", -1)


        binding.idTitleText.text = title
        binding.idDesccriptionText.text = description
        binding.idRecipeText.text = recipe

        var img:Uri? = null
        if(position != -1)
        {
            val imageView = binding.idNewItemImg
            val _img = cur_data.live_data_cocktails.value?.get(position)?._img
            if (_img != null) {
                Glide.with(binding.root)
                    .load(_img)
                    .into(imageView)
            } else {
                Glide.with(binding.root)
                    .load(R.drawable.place_holder)
                    .into(imageView)
            }
        }
        else
        {
            val imageView = binding.idNewItemImg
            img = intent.getParcelableExtra<Uri>("image")
            if (img!= null) {
                Glide.with(binding.root)
                    .load(img)
                    .into(imageView)
            } else {
                Glide.with(binding.root)
                    .load(R.drawable.place_holder)
                    .into(imageView)
            }
        }



        idEditBtn.setOnClickListener {
            val intent = Intent(this@cocktail_view_activity, save_cocktail_view::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("recipe", recipe)
            intent.putExtra("image", img)


            intent.putExtra("edit", "true")
            intent.putExtra("position", position)

            startActivity(intent)
        }
    }

}