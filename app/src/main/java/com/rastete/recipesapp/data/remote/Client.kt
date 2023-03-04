package com.rastete.recipesapp.data.remote

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Client {

    companion object {
        const val BASE_URL = "https://api.spoonacular.com"
    }

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries : Map<String, String>
    ) : Recipes

}
