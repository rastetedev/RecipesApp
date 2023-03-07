package com.rastete.recipesapp.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@kotlinx.serialization.Serializable
data class Recipes(
    val results: List<Result>
)

@kotlinx.serialization.Serializable
data class Result(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients: List<ExtendedIngredient>,
    val glutenFree: Boolean,
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
)

@Parcelize
@kotlinx.serialization.Serializable
data class ExtendedIngredient(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
) : Parcelable