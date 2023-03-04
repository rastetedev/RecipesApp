package com.rastete.recipesapp.data.repository

import com.rastete.recipesapp.data.local.RecipeDao
import com.rastete.recipesapp.data.remote.Client
import com.rastete.recipesapp.domain.Recipe
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val dao: RecipeDao,
    private val client: Client
) {

    fun getRecipes(): List<Recipe> {
        return emptyList()
    }

}