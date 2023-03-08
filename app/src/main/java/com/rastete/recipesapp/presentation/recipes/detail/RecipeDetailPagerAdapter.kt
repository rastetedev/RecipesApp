package com.rastete.recipesapp.presentation.recipes.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rastete.recipesapp.domain.Recipe
import com.rastete.recipesapp.presentation.recipes.detail.ingredients.IngredientsRecipeFragment
import com.rastete.recipesapp.presentation.recipes.detail.instructions.InstructionsRecipeFragment
import com.rastete.recipesapp.presentation.recipes.detail.overview.OverviewRecipeFragment

class RecipeDetailPagerAdapter(
    fragment: Fragment,
    private val recipe: Recipe
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle().apply {
            putParcelable("recipe", recipe)
        }
        return when (position) {
            0 -> {
                return OverviewRecipeFragment().apply {
                    arguments = bundle
                }
            }
            1 -> IngredientsRecipeFragment().apply {
                arguments = bundle
            }
            2 -> InstructionsRecipeFragment().apply {
                arguments = bundle
            }
            else -> throw IllegalStateException("Position $position is invalid for this viewpager")
        }
    }
}