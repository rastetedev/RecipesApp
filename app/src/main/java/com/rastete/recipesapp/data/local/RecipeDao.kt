package com.rastete.recipesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipeEntity: List<RecipeEntity>)

    @Query("SELECT * FROM RecipeEntity ORDER BY id")
    fun getRecipes(): List<RecipeEntity>
}