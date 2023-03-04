package com.rastete.recipesapp.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rastete.recipesapp.data.local.RecipeDatabase
import com.rastete.recipesapp.data.remote.Client
import com.rastete.recipesapp.data.remote.Client.Companion.BASE_URL
import com.rastete.recipesapp.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRecipeDatabase(@ApplicationContext context: Context): RecipeDatabase {
        return RecipeDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun providesClient(retrofit: Retrofit): Client {
        return retrofit.create(Client::class.java)
    }

    @Singleton
    @Provides
    fun providesRecipeRepository(database: RecipeDatabase, client: Client): RecipeRepository {
        return RecipeRepository(database.recipeDao(), client)
    }
}