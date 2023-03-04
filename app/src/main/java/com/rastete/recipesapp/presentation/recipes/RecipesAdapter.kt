package com.rastete.recipesapp.presentation.recipes

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.rastete.recipesapp.R
import com.rastete.recipesapp.domain.Recipe
import com.rastete.recipesapp.databinding.ItemRecipeBinding
import com.rastete.recipesapp.presentation.util.RecipeDiffUtil

class RecipesAdapter(private val onClickItem: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipeHolder>() {

    var recipes: List<Recipe> = emptyList()

    fun setList(newList: List<Recipe>) {
        val diffCallback = RecipeDiffUtil(recipes, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipes = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHolder(binding)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) =
        holder.bind(recipes[position], onClickItem)

}

class RecipeHolder(private val binding: ItemRecipeBinding) : ViewHolder(binding.root) {

    fun bind(recipe: Recipe, onClickItem: (Recipe) -> Unit) {
        with(binding) {
            root.setOnClickListener {
                onClickItem(recipe)
            }
            ivRecipeImageRecipeI.load(recipe.image) {
                crossfade(600)
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_placeholder)
            }
            tvTitleRecipeRecipeI.text = recipe.title
            tvDescriptionRecipeRecipeI.text = Html.fromHtml(recipe.summary, 0)
            tvRatingRecipeI.text = recipe.aggregateLikes.toString()
            tvTimerRecipeItem.text = recipe.readyInMinutes.toString()
            if (!recipe.vegan) {
                tvIsVegaRecipeI.alpha = 0.2f
                ivIsVeganRecipeI.alpha = 0.2f
                tvIsVegaRecipeI.text = binding.root.context.getString(R.string.not_vegan)
            }
        }
    }
}
