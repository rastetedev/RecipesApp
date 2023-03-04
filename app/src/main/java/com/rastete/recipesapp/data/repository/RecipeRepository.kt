package com.rastete.recipesapp.data.repository

import com.rastete.recipesapp.data.local.RecipeDao
import com.rastete.recipesapp.data.remote.Client
import com.rastete.recipesapp.data.RecipeMapper
import com.rastete.recipesapp.data.remote.Recipes
import com.rastete.recipesapp.domain.Recipe
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import com.rastete.recipesapp.data.util.Result
import retrofit2.Response

@ActivityRetainedScoped
class RecipeRepository @Inject constructor(
    private val dao: RecipeDao,
    private val client: Client,
    private val mapper: RecipeMapper
) {

    suspend fun getRecipes(queries: Map<String, String>): Result<List<Recipe>> {
        val response: Response<Recipes>
        val fromLocalData = mapper.fromLocalToDomain(dao.getRecipes())
        try {
            response = client.getRecipes(queries)
            return when {
                response.message().toString().contains("timeout") -> Result.Error(
                    fromLocalData,
                    message = "Timeout"
                )
                response.code() == 402 -> Result.Error(fromLocalData, message = "API Key Limited.")
                response.body() == null -> Result.Error(fromLocalData, message = "No data found")
                response.isSuccessful -> {
                    dao.insertRecipes(mapper.fromRemoteToLocal(response.body()))
                    val newData = mapper.fromLocalToDomain(dao.getRecipes())
                    Result.Success(newData)
                }
                else -> Result.Error(fromLocalData, message = response.message())
            }
        } catch (e: Exception) {
            return Result.Error(fromLocalData, message = e.message)
        }
    }
}