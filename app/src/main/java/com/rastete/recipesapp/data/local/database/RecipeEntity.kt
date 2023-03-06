package com.rastete.recipesapp.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rastete.recipesapp.data.remote.ExtendedIngredient

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
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
)