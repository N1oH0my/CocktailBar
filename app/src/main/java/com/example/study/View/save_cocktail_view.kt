package com.example.study.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.study.MainActivity
import com.example.study.Models.Cocktail
import com.example.study.R
import com.example.study.View.Fragments.HomePageFragment
import com.example.study.databinding.ActivitySaveCocktailViewBinding
import com.example.weatherapp.ViewModels.MainViewModel

class save_cocktail_view : AppCompatActivity() {
    private lateinit var binding: ActivitySaveCocktailViewBinding
    private val cur_data: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_cocktail_view)
        binding = ActivitySaveCocktailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Init()
    }

    private fun Init() = with(binding)
    {
        val imageView = binding.idTitleItemImg
        Glide.with(binding.root)
            .load(R.drawable.place_holder)
            .into(imageView)

        idSaveBtn.setOnClickListener {
            generateCocktailList()
        }
    }
    private fun generateCocktailList()= with(binding) {

        val title = idEditTitle.text.toString()
        val description = idEditDescription.text.toString()
        val recipe = idEditRecipe.text.toString()

        val cocktail = Cocktail(title, description, recipe, null)
        val new_cocktail_list = mutableListOf<Cocktail>()
        new_cocktail_list.add(cocktail)

        val cur_list = cur_data.live_data_cocktails.value
        if (cur_list != null && cur_list.size != 0)
        {
            new_cocktail_list.addAll(cur_list)
        }
        cur_data.live_data_cocktails.value = new_cocktail_list

        /*val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()*/
        /*
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_page, HomePageFragment.newInstance())
            .commit()*/
    }
}