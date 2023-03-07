package com.rastete.recipesapp.presentation.recipes.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rastete.recipesapp.R
import com.rastete.recipesapp.databinding.FragmentRecipesBinding
import com.rastete.recipesapp.presentation.util.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import com.rastete.recipesapp.data.util.Result
import com.rastete.recipesapp.presentation.util.ConnectivityObserver
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by viewModels()

    private val recipesAdapter by lazy {
        RecipesAdapter {
            val action = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailFragment(it)
            findNavController().navigate(action)
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

        lifecycleScope.launch {
            connectivityObserver.observe().collectLatest {
                when (it) {
                    ConnectivityObserver.NetworkStatus.AVAILABLE -> {
                        binding.tvAlertMessageRecipesF.visibility = View.GONE
                        binding.fabOpenFilterRecipesF.isEnabled = true
                    }
                    else -> {
                        binding.tvAlertMessageRecipesF.visibility = View.VISIBLE
                        binding.fabOpenFilterRecipesF.isEnabled = false
                    }
                }
            }
        }

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
                        } else binding.llEmptyDataRecipesF.visibility = View.VISIBLE
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