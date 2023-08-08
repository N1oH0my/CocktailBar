package com.example.study.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.Models.Cocktail
import com.example.study.R

class CocktailListAdapter(var context: Context, private val cocktailList: List<Cocktail>) : RecyclerView.Adapter<CocktailListAdapter.CocktailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.fragment_cocktail_item, parent, false)
        return CocktailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val currentCocktail = cocktailList[position]
        holder.titleTextView.text = currentCocktail._title

        // Загрузка изображения
        val img = currentCocktail._img
        if (img != null) {
            Glide.with(holder.itemView.context)
                .load(img)
                .into(holder.imageView)
        }
        else{
            Glide.with(holder.itemView.context)
                .load(R.drawable.place_holder_img)
                .into(holder.imageView)
        }

    }

    override fun getItemCount() = cocktailList.size

    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.id_recycler_item_title)
        val imageView: ImageView = itemView.findViewById(R.id.id_recycler_item_img)
    }

}