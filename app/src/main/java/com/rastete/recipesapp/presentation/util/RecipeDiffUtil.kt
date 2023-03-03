package com.rastete.recipesapp.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.rastete.recipesapp.data.Recipe

class RecipeDiffUtil(
    private val oldList: List<Recipe>,
    private val newList: List<Recipe>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id &&
                oldItem.title == newItem.title &&
                oldItem.description == newItem.description &&
                oldItem.rating == newItem.rating &&
                oldItem.timer == newItem.timer &&
                oldItem.isVegan == newItem.isVegan
    }
}