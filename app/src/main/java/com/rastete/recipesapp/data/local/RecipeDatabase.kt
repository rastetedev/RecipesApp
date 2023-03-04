package com.rastete.recipesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe_database")
                        .build()
                return instance.also {
                    INSTANCE = it
                }

            }
        }
    }

}