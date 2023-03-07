package com.rastete.recipesapp.presentation.recipes.filters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastete.recipesapp.data.local.file.FilterRecipes
import com.rastete.recipesapp.data.local.file.FiltersDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    private val filtersDataStoreRepository: FiltersDataStoreRepository
) : ViewModel() {

    private val _filterRecipes = MutableLiveData<FilterRecipes>()
    val filterRecipes
        get() = _filterRecipes

    init {
        getFilters()
    }

    private fun getFilters() {
        viewModelScope.launch {
            filtersDataStoreRepository.readFilterRecipes().collectLatest {
                _filterRecipes.value = it
            }
        }
    }

    fun saveFilters(mealTypeName: String, mealTypeId: Int, dietTypeName: String, dietTypeId: Int) {
        viewModelScope.launch {
            filtersDataStoreRepository.saveFilterRecipes(
                FilterRecipes(
                    mealTypeName = mealTypeName,
                    mealTypeId = mealTypeId,
                    dietTypeName = dietTypeName,
                    dietTypeId = dietTypeId
                )
            )
        }
    }
}