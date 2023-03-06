package com.rastete.recipesapp.data.local.database

import androidx.room.*

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipeEntity: List<RecipeEntity>)

    @Query("SELECT * FROM RecipeEntity ORDER BY id")
    fun getRecipes(): List<RecipeEntity>

    @Query("DELETE FROM RecipeEntity")
    fun deleteRecipes()
}