package com.rastete.recipesapp.presentation.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rastete.recipesapp.R
import com.rastete.recipesapp.databinding.FragmentRecipesBinding
import com.rastete.recipesapp.presentation.util.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import com.rastete.recipesapp.data.util.Result

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by viewModels()

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

        setupEvents()
        setupAdapter()
        setupObservers()
    }

    private fun setupEvents() {
        binding.fabOpenFilterRecipesF.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_filtersBottomSheetFragment)
        }
    }

    private fun setupObservers() {
        recipesViewModel.recipesResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.pbLoadingRecipesF.visibility = View.VISIBLE
                    binding.llEmptyDataRecipesF.visibility = View.GONE
                    binding.rvRecipesRecipesF.visibility = View.INVISIBLE
                }
                is Result.Error,
                is Result.Success -> {
                    binding.pbLoadingRecipesF.visibility = View.GONE
                    result.data?.let {
                        if (it.isNotEmpty()) {
                            recipesAdapter.setList(it)
                            binding.llEmptyDataRecipesF.visibility = View.GONE
                            binding.rvRecipesRecipesF.visibility = View.VISIBLE
                        }
                        else binding.llEmptyDataRecipesF.visibility = View.VISIBLE
                    }
                    if (result is Result.Error) {
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvRecipesRecipesF.adapter = recipesAdapter
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvRecipesRecipesF.layoutManager = linearLayoutManager

        binding.rvRecipesRecipesF.addItemDecoration(MarginItemDecoration())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}