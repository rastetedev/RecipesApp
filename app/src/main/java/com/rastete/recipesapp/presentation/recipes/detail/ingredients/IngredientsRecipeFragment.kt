package com.rastete.recipesapp.presentation.recipes.detail.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rastete.recipesapp.databinding.FragmentIngredientsRecipeBinding
import com.rastete.recipesapp.domain.Recipe
import com.rastete.recipesapp.presentation.util.MarginItemDecoration
import com.rastete.recipesapp.presentation.util.parcelable

class IngredientsRecipeFragment : Fragment() {

    private var _binding: FragmentIngredientsRecipeBinding? = null
    private val binding get() = _binding!!

    private val recipe by lazy {
        arguments?.parcelable<Recipe>("recipe")
    }

    private val ingredientsAdapter by lazy {
        IngredientsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        ingredientsAdapter.setList(recipe?.extendedIngredients ?: emptyList())
    }

    private fun setupAdapter() {
        binding.rvIngredientsIngredientsRecipeF.adapter = ingredientsAdapter
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvIngredientsIngredientsRecipeF.layoutManager = linearLayoutManager
        binding.rvIngredientsIngredientsRecipeF.addItemDecoration(MarginItemDecoration())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}