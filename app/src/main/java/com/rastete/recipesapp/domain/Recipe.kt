package com.rastete.recipesapp.domain

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Int,
    val timer : Int,
    val isVegan: Boolean
)