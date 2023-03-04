package com.rastete.recipesapp.presentation.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.recipesapp.BuildConfig
import com.rastete.recipesapp.data.remote.Client.Companion.ADD_RECIPE_INFORMATION_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.API_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.FILL_INGREDIENTS_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.NUMBER_KEY
import com.rastete.recipesapp.data.repository.RecipeRepository
import com.rastete.recipesapp.domain.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.rastete.recipesapp.data.util.Result

@HiltViewModel
class RecipesViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    private val _recipesResponse = MutableLiveData<Result<List<Recipe>>>()
    val recipesResponse: LiveData<Result<List<Recipe>>>
        get() = _recipesResponse

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            _recipesResponse.value = Result.Loading()
            try {
                _recipesResponse.value = repository.getRecipes(
                    buildQueries()
                )
            } catch (e: Exception) {
                _recipesResponse.value = Result.Error(message = e.message)
            }
        }
    }
}

private fun buildQueries(): Map<String, String> {
    return mapOf(
        API_KEY to BuildConfig.SPOONACULAR_API_KEY,
        NUMBER_KEY to 50.toString(),
        FILL_INGREDIENTS_KEY to true.toString(),
        ADD_RECIPE_INFORMATION_KEY to true.toString()
    )
}

