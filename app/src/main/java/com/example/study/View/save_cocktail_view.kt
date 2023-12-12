package com.example.study.View

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.study.MainActivity
import com.example.study.Models.Cocktail
import com.example.study.R
import com.example.study.View.Fragments.HomePageFragment
import com.example.study.ViewModel.DatabaseHelper
import com.example.study.databinding.ActivitySaveCocktailViewBinding
import com.example.weatherapp.ViewModels.MainViewModel

class save_cocktail_view : AppCompatActivity() {
    private lateinit var binding: ActivitySaveCocktailViewBinding
    private val cur_data: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var PICK_IMAGE_REQUEST = 1
    private var cocktail = Cocktail(null," ", " ", " ", null)
    var position: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_cocktail_view)
        binding = ActivitySaveCocktailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val is_edit = intent.getStringExtra("edit")
        if (is_edit == "true") {
            InitEdit()
        }
        else{
            InitAdd()
        }


    }

    private fun InitEdit() = with(binding)
    {
        position = intent.getIntExtra("position", -1)
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val recipe = intent.getStringExtra("recipe")

        idEditTitle.setText(title)
        idEditDescription.setText(description)
        idEditRecipe.setText(recipe)



        idCancelBtn.setOnClickListener {
            onBackPressed()
        }


        val imageView = binding.idNewItemImg
        var imageUri: Uri? = null
        if (position != -1)
        {
            idSaveBtn.setOnClickListener {
                updateCocktailList()
                //onBackPressed()
            }
        }
        /*else{
            idSaveBtn.setOnClickListener {
                generateCocktailList()
                onBackPressed()
            }
        }*/

        imageUri = intent.getParcelableExtra<Uri>("image")
        if (imageUri != null) {
            Glide.with(binding.root)
                .load(imageUri)
                .into(imageView)
            cocktail._img = imageUri
        }
        else {
        Glide.with(binding.root)
            .load(R.drawable.place_holder)
            .into(imageView)
        }


        idNewItemImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }
    private fun InitAdd() = with(binding)
    {
        val imageView = binding.idNewItemImg
        Glide.with(binding.root)
            .load(R.drawable.place_holder)
            .into(imageView)

        idCancelBtn.setOnClickListener {
            onBackPressed()
        }
        idSaveBtn.setOnClickListener {
            generateCocktailList()
            onBackPressed()
        }

        idNewItemImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }
    private fun generateCocktailList()= with(binding) {

        val title = idEditTitle.text.toString()
        val description = idEditDescription.text.toString()
        val recipe = idEditRecipe.text.toString()

        cocktail._title = title
        cocktail._description = description
        cocktail._recipe = recipe



        val new_cocktail_list = mutableListOf<Cocktail>()
        val dbHelper = DatabaseHelper(this@save_cocktail_view)
        val id = dbHelper.addCocktail(cocktail)
        cocktail._id = id
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            cocktail._img = selectedImage//.toString()
            val imageView = binding.idNewItemImg
            Glide.with(binding.root)
                .load(selectedImage)
                .into(imageView)
        }
    }

    private fun updateCocktailList()= with(binding) {

        val title = idEditTitle.text.toString()
        val description = idEditDescription.text.toString()
        val recipe = idEditRecipe.text.toString()

        cocktail._title = title
        cocktail._description = description
        cocktail._recipe = recipe



        val new_list = cur_data.live_data_cocktails.value
        if (new_list != null && new_list.size != 0)
        {
            cocktail._id = new_list[position]._id
            new_list[position] = cocktail
            val dbHelper = DatabaseHelper(this@save_cocktail_view)
            dbHelper.updateCocktail(cocktail)
        }
        cur_data.live_data_cocktails.value = new_list


        val intent = Intent(this@save_cocktail_view, cocktail_view_activity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("description", description)
        intent.putExtra("recipe", recipe)
        if(cocktail._img != null)
            intent.putExtra("image", cocktail._img)

        intent.putExtra("position", position)

        startActivity(intent)
    }


}