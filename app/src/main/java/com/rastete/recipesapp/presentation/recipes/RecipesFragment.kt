package com.rastete.recipesapp.presentation.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rastete.recipesapp.domain.Recipe
import com.rastete.recipesapp.databinding.FragmentRecipesBinding
import com.rastete.recipesapp.presentation.util.MarginItemDecoration

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesAdapter by lazy {
        RecipesAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    private fun setupAdapter() {
        binding.rvRecipesRecipesF.adapter = recipesAdapter
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvRecipesRecipesF.layoutManager = linearLayoutManager

        binding.rvRecipesRecipesF.addItemDecoration(MarginItemDecoration())

        recipesAdapter.setList(
            listOf(
                Recipe(1, "New Title Test", "New description Test", "", 100, 100, true),
                Recipe(2, "New Title Test", "New description Test", "", 100, 100, false)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}