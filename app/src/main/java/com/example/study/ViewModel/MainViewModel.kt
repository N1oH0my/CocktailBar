package com.example.weatherapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.Models.Cocktail

object MainViewModel: ViewModel() {
    var live_data_cocktails = MutableLiveData<MutableList<Cocktail>>()
}