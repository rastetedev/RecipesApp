package com.rastete.recipesapp.data.local.database

import androidx.room.TypeConverter
import com.rastete.recipesapp.data.remote.ExtendedIngredient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ExtendedIngredientListTypeConverter {

    @TypeConverter
    fun transformExtendedIngredientListToString(extendedIngredientList: List<ExtendedIngredient>): String {
        return Json.encodeToString(extendedIngredientList)
    }

    @TypeConverter
    fun transformStringToExtendedIngredientList(value: String): List<ExtendedIngredient> {
        return Json.decodeFromString(value)
    }
}