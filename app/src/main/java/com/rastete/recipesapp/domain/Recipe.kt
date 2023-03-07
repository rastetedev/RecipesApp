package com.rastete.recipesapp.domain

import android.os.Parcelable
import com.rastete.recipesapp.data.remote.ExtendedIngredient
import kotlinx.parcelize.Parcelize

@kotlinx.serialization.Serializable
@Parcelize
data class Recipe(
    val id: Int = 0,
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients: List<ExtendedIngredient>,
    val glutenFree: Boolean,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
) : Parcelable