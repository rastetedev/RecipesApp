package com.rastete.recipesapp.presentation.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.recipesapp.BuildConfig
import com.rastete.recipesapp.data.local.file.FiltersDataStoreRepository
import com.rastete.recipesapp.data.remote.Client.Companion.ADD_RECIPE_INFORMATION_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.API_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.FILL_INGREDIENTS_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.NUMBER_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.QUERY_DIET_TYPE_KEY
import com.rastete.recipesapp.data.remote.Client.Companion.QUERY_MEAL_TYPE_KEY
import com.rastete.recipesapp.data.repository.RecipeRepository
import com.rastete.recipesapp.domain.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.rastete.recipesapp.data.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val filtersRepository: FiltersDataStoreRepository
) : ViewModel() {

    private val _recipesResponse = MutableLiveData<Result<List<Recipe>>>()
    val recipesResponse: LiveData<Result<List<Recipe>>>
        get() = _recipesResponse

    private var mealType: String = ""
    private var dietType: String = ""

    init {
        observeFilters()
    }

    private fun observeFilters() {
        viewModelScope.launch {
            filtersRepository.readFilterRecipes().collectLatest { filterRecipe ->
                mealType = filterRecipe.mealTypeName
                dietType = filterRecipe.dietTypeName

                getRecipes()
            }
        }
    }

    private fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _recipesResponse.value = Result.Loading()
            }
            _recipesResponse.postValue(repository.getRecipes(buildQueries()))
        }
    }

    private fun buildQueries(): Map<String, String> {
        return mutableMapOf(
            API_KEY to BuildConfig.SPOONACULAR_API_KEY,
            NUMBER_KEY to 50.toString(),
            FILL_INGREDIENTS_KEY to true.toString(),
            ADD_RECIPE_INFORMATION_KEY to true.toString()
        ).also { queries ->
            if (mealType.isNotEmpty()) {
                queries[QUERY_MEAL_TYPE_KEY] = mealType
            }
            if (dietType.isNotEmpty()) {
                queries[QUERY_DIET_TYPE_KEY] = dietType
            }
        }
    }
}



