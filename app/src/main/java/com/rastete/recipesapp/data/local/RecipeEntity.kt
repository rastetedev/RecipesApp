package com.rastete.recipesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rastete.recipesapp.domain.Recipe

@Entity
data class RecipeEntity(
    private val recipe: Recipe
) {
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
}