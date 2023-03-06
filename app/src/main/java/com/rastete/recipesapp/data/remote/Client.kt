package com.rastete.recipesapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Client {

    companion object {
        const val BASE_URL = "https://api.spoonacular.com"

        const val API_KEY = "apiKey"
        const val NUMBER_KEY = "number"
        const val FILL_INGREDIENTS_KEY = "fillIngredients"
        const val ADD_RECIPE_INFORMATION_KEY = "addRecipeInformation"
        const val QUERY_DIET_TYPE_KEY = "diet"
        const val QUERY_MEAL_TYPE_KEY = "type"
    }

    @GET("/recipes/complexSearch/")
    suspend fun getRecipes(
        @QueryMap queries : Map<String, String>
    ) : Response<Recipes>

}
