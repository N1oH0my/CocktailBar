package com.example.study.View.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.study.R
import com.example.study.databinding.FragmentHomePageBinding
import com.example.study.View.save_cocktail_view
import com.example.weatherapp.ViewModels.MainViewModel
import androidx.lifecycle.ViewModelProvider


class HomePageFragment : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private val cur_data: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit =with(_binding) {
        super.onViewCreated(view, savedInstanceState)

        Init()
        Launch()
        Update()

    }
    companion object {
        @JvmStatic
        fun newInstance() = HomePageFragment()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun Init() = with(_binding)
    {
        /*this?.idTitleItemImg?.setOnClickListener {
            val rootView = view.findViewById<ViewGroup>(R.id.id_scroll_view)

            // Удаление всех дочерних элементов из корневого представления
            rootView.removeAllViews()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = CocktailsListFragment()
            fragmentTransaction.replace(R.id.id_scroll_view, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }*/

        this?.idAddBtn?.setOnClickListener {
            val intent = Intent(requireContext(), save_cocktail_view::class.java)
            startActivity(intent)
        }
    }

    private fun Launch()
    {
        val list = cur_data.live_data_cocktails.value
        if (list != null && list.size != 0)
        {
            val rootView = view?.findViewById<ViewGroup>(R.id.id_scroll_view)

            // Удаление всех дочерних элементов из корневого представления
            if (rootView != null) {
                rootView.removeAllViews()
            }
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = CocktailsListFragment()
            fragmentTransaction.replace(R.id.id_scroll_view, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
    private fun Update()
    {
        cur_data.live_data_cocktails.observe(viewLifecycleOwner) { cocktails ->
            Launch()
        }
    }
}