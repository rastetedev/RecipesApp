package com.rastete.recipesapp.presentation.recipes.detail.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.rastete.recipesapp.R
import com.rastete.recipesapp.databinding.FragmentOverviewRecipeBinding
import com.rastete.recipesapp.domain.Recipe
import org.jsoup.Jsoup


class OverviewRecipeFragment : Fragment() {

    private var _binding: FragmentOverviewRecipeBinding? = null
    private val binding get() = _binding!!

    private val recipe by lazy {
        arguments?.getParcelable<Recipe>("recipe")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recipe?.let {
                ivRecipeOverviewRecipeF.load(it.image)
                tvTimeOverviewRecipeF.text = it.readyInMinutes.toString()
                tvLikesOverviewRecipeF.text = it.aggregateLikes.toString()
                tvTitleOverviewRecipeF.text = it.title
                ivVeganCheckIconOverviewRecipeF.setCheckColor(it.vegan)
                tvVeganCheckOverviewRecipeF.setCheckColor(it.vegan)

                ivVegetarianCheckIconOverviewRecipeF.setCheckColor(it.vegetarian)
                tvVegetarianCheckOverviewRecipeF.setCheckColor(it.vegetarian)

                ivGlutenFreeCheckIconOverviewRecipeF.setCheckColor(it.glutenFree)
                tvGlutenFreeCheckOverviewRecipeF.setCheckColor(it.glutenFree)

                ivDairyFreeCheckIconOverviewRecipeF.setCheckColor(it.dairyFree)
                tvDairyFreeCheckOverviewRecipeF.setCheckColor(it.dairyFree)

                ivHealthyCheckIconOverviewRecipeF.setCheckColor(it.veryHealthy)
                tvHealthyCheckOverviewRecipeF.setCheckColor(it.veryHealthy)

                ivCheapCheckIconOverviewRecipeF.setCheckColor(it.cheap)
                tvCheapCheckOverviewRecipeF.setCheckColor(it.cheap)

                tvSummaryOverviewRecipeF.escapeHtml(it.summary)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun ImageView.setCheckColor(hasCheck: Boolean) {
    this.imageTintList =
        ContextCompat.getColorStateList(context, if (hasCheck) R.color.green else R.color.darkGray)
}

fun TextView.setCheckColor(hasCheck: Boolean) =
    this.setTextColor(if (hasCheck) context.getColor(R.color.green) else context.getColor(R.color.darkGray))

fun TextView.escapeHtml(text: String) {
    val plainText = Jsoup.parse(text).text()
    this.text = plainText
}