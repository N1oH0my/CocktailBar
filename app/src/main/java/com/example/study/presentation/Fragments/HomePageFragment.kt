package com.example.study.presentation.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.study.R
import com.example.study.databinding.FragmentHomePageBinding
import com.example.study.presentation.View.save_cocktail_view
import com.example.weatherapp.ViewModels.MainViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.study.framework.database.DatabaseHelper


class HomePageFragment : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private val cur_data: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val REQUEST_CODE_PERMISSION = 1001
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit =with(_binding) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DatabaseHelper(requireContext())
        val list = dbHelper.getAllCocktails()
        cur_data.live_data_cocktails.value = list
        Init()
        Update()
        Launch()




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


        this?.idAddBtn?.setOnClickListener {
            val intent = Intent(requireContext(), save_cocktail_view::class.java)
            startActivity(intent)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R &&
                Environment.isExternalStorageManager()) {

            } else {

                Toast.makeText(requireContext(), "no permissions for files", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun Launch()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R &&
            !Environment.isExternalStorageManager()) {
            val uri = Uri.parse("package:" + requireActivity().packageName)
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri)
            startActivityForResult(intent, REQUEST_CODE_PERMISSION)
        }
        val list = cur_data.live_data_cocktails.value
        if (list != null && list.size != 0)
        {
            val rootView = view?.findViewById<ViewGroup>(R.id.id_scroll_view)

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