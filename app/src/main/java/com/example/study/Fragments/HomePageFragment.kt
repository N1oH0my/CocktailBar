package com.example.study.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.study.R
import com.example.study.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {
    private var _binding: FragmentHomePageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        /*_binding!!.idTitleItemImg.setOnClickListener {
            val fragment = CocktailsListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.id_linear_view, fragment)
                .commit()
        }*/

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.idTitleItemImg.setOnClickListener {
            val rootView = view.findViewById<ViewGroup>(R.id.id_scroll_view)

            // Удаление всех дочерних элементов из корневого представления
            rootView.removeAllViews()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = CocktailsListFragment()
            fragmentTransaction.replace(R.id.id_scroll_view, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }
    companion object {
        @JvmStatic
        fun newInstance() = HomePageFragment()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}