package com.rastete.recipesapp.data.repository

import com.rastete.recipesapp.data.local.RecipeDao
import com.rastete.recipesapp.data.remote.Client
import com.rastete.recipesapp.data.remote.RecipeRemoteClientMapper
import com.rastete.recipesapp.domain.Recipe
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import com.rastete.recipesapp.data.util.Result

@ActivityRetainedScoped
class RecipeRepository @Inject constructor(
    private val dao: RecipeDao,
    private val client: Client,
    private val mapper: RecipeRemoteClientMapper
) {

    suspend fun getRecipes(queries: Map<String, String>): Result<List<Recipe>> {
        val response = client.getRecipes(queries)
        return when {
            response.message().toString().contains("timeout") -> Result.Error(message = "Timeout")
            response.code() == 402 -> Result.Error(message = "API Key Limited.")
            response.body() == null -> Result.Error(message = "No data found")
            response.isSuccessful -> Result.Success(mapper.toDomain(response.body()))
            else -> Result.Error(message = response.message())
        }
    }
}