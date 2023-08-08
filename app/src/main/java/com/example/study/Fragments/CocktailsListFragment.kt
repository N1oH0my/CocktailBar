package com.example.study.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.Adapters.CocktailListAdapter
import com.example.study.Models.Cocktail
import com.example.study.R
import com.example.study.cocktail_view_activity
import com.example.study.databinding.FragmentCocktailsListBinding


class CocktailsListFragment : Fragment() {
    private lateinit var binding: FragmentCocktailsListBinding
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
        // Инициализация RecyclerView и установка адаптера
        val recyclerView = view.findViewById<RecyclerView>(R.id.id_cocktail_recycler)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Отображение по 2 элемента в линии
        recyclerView.adapter = adapter//CocktailListAdapter(requireContext(), getDummyProductList())


        adapter.onItemClickListener = object : CocktailListAdapter.OnItemClickListener {
            override fun onItemClick(item: Cocktail) {
                // Здесь открывайте новую активность (или фрагмент) с информацией о выбранном элементе
                val intent = Intent(requireContext(), cocktail_view_activity::class.java)
                intent.putExtra("title", item._title)
                intent.putExtra("description", item._description)
                intent.putExtra("recipe", item._recipe)
                intent.putExtra("image", item._img)
                startActivity(intent)
            }
        }

    }

    // Метод для получения списка товаров
    private fun getDummyProductList(): List<Cocktail> {
        // Здесь вы можете получить списо�� товаров из вашего источника данных
        // В данном примере мы создаем фиктивный список товаров
        val productList = mutableListOf<Cocktail>()

        productList.add(Cocktail("first", "Description ", "Recipe", null))
        productList.add(Cocktail("second", "Description ", "Recipe", null))
        productList.add(Cocktail("third", "Description ", "Recipe", null))

        return productList
    }
}