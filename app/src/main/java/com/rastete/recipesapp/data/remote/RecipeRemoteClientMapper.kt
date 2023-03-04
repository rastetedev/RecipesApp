package com.rastete.recipesapp.data.remote

import com.rastete.recipesapp.domain.Recipe

class RecipeRemoteClientMapper {

    fun toDomain(recipes: Recipes?): List<Recipe> {
        return recipes?.results?.map {
            Recipe(
                it.id,
                it.title,
                it.summary,
                it.image,
                it.aggregateLikes,
                it.readyInMinutes,
                it.vegan
            )
        } ?: emptyList()
    }
}