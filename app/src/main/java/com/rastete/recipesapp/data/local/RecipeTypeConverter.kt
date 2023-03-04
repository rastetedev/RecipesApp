package com.rastete.recipesapp.data.local

import androidx.room.TypeConverter
import com.rastete.recipesapp.domain.Recipe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RecipeTypeConverter {

    @TypeConverter
    fun transformRecipeToString(recipe: Recipe): String {
        return Json.encodeToString(recipe)
    }

    @TypeConverter
    fun transformStringToRecipe(value: String): Recipe {
        return Json.decodeFromString(value)
    }
}