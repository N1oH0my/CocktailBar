package com.example.study.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.databinding.ActivityCocktailViewBinding
import com.example.weatherapp.ViewModels.MainViewModel


class cocktail_view_activity : AppCompatActivity() {
    private lateinit var binding: ActivityCocktailViewBinding
    private val cur_data: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Init()
    }
    override fun onResume() {
        super.onResume()
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


        var imgUri: Uri? = null
        val imageView = binding.idItemImg
        imgUri = intent.getParcelableExtra<Uri>("image")
        if (imgUri!= null) {
            Glide.with(binding.root)
                .load(imgUri)
                .into(imageView)
        }
        else
        {
            Glide.with(binding.root)
                .load(R.drawable.place_holder)
                .into(imageView)
        }

        idEditBtn.setOnClickListener {
            val intent = Intent(this@cocktail_view_activity, save_cocktail_view::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("recipe", recipe)
            intent.putExtra("image", imgUri)


            intent.putExtra("edit", "true")
            intent.putExtra("position", position)

            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        finish()
    }



}