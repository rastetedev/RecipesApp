package com.rastete.recipesapp.data.local.file

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.recipeDataStore: DataStore<Preferences> by preferencesDataStore("filters_recipes")

data class FilterRecipes(
    val mealTypeId: Int = -1,
    val mealTypeName: String = "",
    val dietTypeId: Int = -1,
    val dietTypeName: String = ""
)

@ActivityRetainedScoped
class FiltersDataStoreRepository @Inject constructor(private val context: Context) {

    suspend fun saveFilterRecipes(filterRecipes: FilterRecipes) {
        context.recipeDataStore.edit { filters ->
            filters[intPreferencesKey(MEAL_TYPE_ID)] = filterRecipes.mealTypeId
            filters[stringPreferencesKey(MEAL_TYPE_NAME)] = filterRecipes.mealTypeName
            filters[intPreferencesKey(DIET_TYPE_ID)] = filterRecipes.dietTypeId
            filters[stringPreferencesKey(DIET_TYPE_NAME)] = filterRecipes.dietTypeName
        }
    }

    fun readFilterRecipes(): Flow<FilterRecipes> =
        context.recipeDataStore.data
            .map {
                FilterRecipes(
                    it[intPreferencesKey(MEAL_TYPE_ID)] ?: -1,
                    it[stringPreferencesKey(MEAL_TYPE_NAME)] ?: "",
                    it[intPreferencesKey(DIET_TYPE_ID)] ?: -1,
                    it[stringPreferencesKey(DIET_TYPE_NAME)] ?: "",
                )
            }


    companion object {
        const val MEAL_TYPE_ID = "mealTypeId"
        const val MEAL_TYPE_NAME = "mealTypeName"
        const val DIET_TYPE_ID = "dietTypeId"
        const val DIET_TYPE_NAME = "dietTypeName"
    }
}

