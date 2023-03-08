package com.rastete.recipesapp.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.rastete.recipesapp.data.remote.ExtendedIngredient

class IngredientDiffUtil(
    private val oldList: List<ExtendedIngredient>,
    private val newList: List<ExtendedIngredient>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.name == newItem.name &&
                oldItem.amount == newItem.amount &&
                oldItem.image == newItem.image &&
                oldItem.consistency == newItem.consistency &&
                oldItem.original == newItem.original &&
                oldItem.unit == newItem.unit
    }
}