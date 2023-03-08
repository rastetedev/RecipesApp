package com.rastete.recipesapp.presentation.recipes.detail.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.rastete.recipesapp.R
import com.rastete.recipesapp.data.remote.ExtendedIngredient
import com.rastete.recipesapp.databinding.ItemIngredientBinding
import com.rastete.recipesapp.presentation.util.IngredientDiffUtil

class IngredientsAdapter :
    RecyclerView.Adapter<IngredientHolder>() {

    var ingredients: List<ExtendedIngredient> = emptyList()

    fun setList(newList: List<ExtendedIngredient>) {
        val diffCallback = IngredientDiffUtil(ingredients, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        ingredients = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val binding =
            ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientHolder(binding)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) =
        holder.bind(ingredients[position])

}

class IngredientHolder(private val binding: ItemIngredientBinding) : ViewHolder(binding.root) {

    fun bind(ingredient: ExtendedIngredient) {
        with(binding) {

            ivIngredientImageIngredientI.load(ingredient.image) {
                crossfade(600)
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_placeholder)
            }
            tvIngredientNameIngredientI.text = ingredient.name
            tvIngredientAmountIngredientI.text = ingredient.amount.toString()
            tvIngredientConsistencyIngredientI.text = ingredient.consistency
            tvIngredientUnitIngredientI.text = ingredient.unit
            tvIngredientOriginalIngredientI.text = ingredient.original
        }
    }
}
