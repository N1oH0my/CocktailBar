package com.example.study.View.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.Adapters.CocktailListAdapter
import com.example.study.Models.Cocktail
import com.example.study.R
import com.example.study.View.cocktail_view_activity
import com.example.study.databinding.FragmentCocktailsListBinding
import com.example.weatherapp.ViewModels.MainViewModel


class CocktailsListFragment : Fragment() {
    private lateinit var binding: FragmentCocktailsListBinding
    private val cur_data: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCocktailsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CocktailListAdapter(requireContext(), getDummyProductList())

        val recyclerView = view.findViewById<RecyclerView>(R.id.id_cocktail_recycler)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Отображение по 2 элемента в линии
        recyclerView.adapter = adapter


        adapter.onItemClickListener = object : CocktailListAdapter.OnItemClickListener {
            override fun onItemClick(item: Cocktail, position: Int) {
                // Здесь открывайте новую активность (или фрагмент) с информацией о выбранном элементе
                val intent = Intent(requireContext(), cocktail_view_activity::class.java)
                intent.putExtra("title", item._title)
                intent.putExtra("description", item._description)
                intent.putExtra("recipe", item._recipe)
                intent.putExtra("image", item._img)

                intent.putExtra("position", position)
                startActivity(intent)
            }
        }

    }

    private fun getDummyProductList(): List<Cocktail> {

        val cur_cocktail_list = cur_data.live_data_cocktails.value//mutableListOf<Cocktail>()
        var cocktail_list = mutableListOf<Cocktail>()
        if (cur_cocktail_list != null)
        {
            cocktail_list.addAll(cur_cocktail_list)
        }
        return cocktail_list

    }
}