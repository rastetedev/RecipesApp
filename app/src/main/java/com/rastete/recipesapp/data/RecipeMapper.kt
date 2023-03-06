package com.rastete.recipesapp.data

import com.rastete.recipesapp.data.local.database.RecipeEntity
import com.rastete.recipesapp.data.remote.Recipes
import com.rastete.recipesapp.domain.Recipe

class RecipeMapper {

    fun fromRemoteToLocal(recipes: Recipes?): List<RecipeEntity> {
        return recipes?.results?.map {
            RecipeEntity(
                id = it.id,
                aggregateLikes = it.aggregateLikes,
                cheap = it.cheap,
                dairyFree = it.dairyFree,
                extendedIngredients = it.extendedIngredients,
                glutenFree = it.glutenFree,
                image = it.image,
                readyInMinutes = it.readyInMinutes,
                sourceName = it.sourceName,
                sourceUrl = it.sourceUrl,
                summary = it.summary,
                title = it.title,
                vegan = it.vegan,
                vegetarian = it.vegetarian,
                veryHealthy = it.veryHealthy
            )
        } ?: emptyList()
    }

    fun fromLocalToDomain(recipeEntity: RecipeEntity): Recipe {
        with(recipeEntity) {
            return Recipe(
                id,
                aggregateLikes,
                cheap,
                dairyFree,
                extendedIngredients,
                glutenFree,
                image,
                readyInMinutes,
                sourceName,
                sourceUrl,
                summary,
                title,
                vegan,
                vegetarian,
                veryHealthy

            )
        }
    }

    fun fromLocalToDomain(recipeEntities: List<RecipeEntity>): List<Recipe> =
        recipeEntities.map { fromLocalToDomain(it) }
}