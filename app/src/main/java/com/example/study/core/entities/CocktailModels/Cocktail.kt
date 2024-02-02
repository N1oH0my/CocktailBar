package com.example.study.core.entities.CocktailModels

import android.net.Uri

data class Cocktail(
    var _id: Int? = null,
    var _title: String?,
    var _description: String?,
    var _recipe: String?,

    var _img: Uri?,
)
