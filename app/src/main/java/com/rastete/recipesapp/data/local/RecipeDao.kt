package com.rastete.recipesapp.data.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RecipeDao {

    @Insert
    fun insertRecipe(recipeEntity: RecipeEntity)
}