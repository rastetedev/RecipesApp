package com.rastete.recipesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)