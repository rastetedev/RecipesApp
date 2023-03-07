package com.rastete.recipesapp.presentation.recipes.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.rastete.recipesapp.R
import com.rastete.recipesapp.databinding.FragmentFiltersBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFiltersBottomSheetBinding? = null
    private val binding get() = _binding!!


    private val filtersViewModel: FiltersViewModel by viewModels()

    private var mealTypeId: Int = -1
    private var mealTypeName: String = ""
    private var dietTypeId: Int = -1
    private var dietTypeName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.chipGMealTypesFilterBSF.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                mealTypeId = checkedIds[0]
                val chip = group.findViewById<Chip>(mealTypeId)
                mealTypeName = chip.text.toString().lowercase()
            } else {
                mealTypeId = -1
                mealTypeName = ""
            }
        }
        binding.chipGDietTypeFiltersBSF.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                dietTypeId = checkedIds[0]
                val chip = group.findViewById<Chip>(checkedIds[0])
                dietTypeName = chip.text.toString().lowercase()
            } else {
                dietTypeId = -1
                dietTypeName = ""
            }
        }

        binding.btnApplyFiltersBSF.setOnClickListener {
            filtersViewModel.saveFilters(
                mealTypeName,
                mealTypeId,
                dietTypeName,
                dietTypeId
            )
            findNavController().navigate(R.id.action_filtersBottomSheetFragment_to_recipesFragment)
        }
    }

    private fun setupObservers() {
        filtersViewModel.filterRecipes.observe(viewLifecycleOwner) {
            if (it.mealTypeId != -1) {
                binding.chipGMealTypesFilterBSF.check(it.mealTypeId)
                mealTypeId = it.mealTypeId
            }
            if (it.dietTypeId != -1) {
                binding.chipGDietTypeFiltersBSF.check(it.dietTypeId)
                dietTypeId = it.dietTypeId
            }
        }
    }

}