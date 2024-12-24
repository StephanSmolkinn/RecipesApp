package com.project.recipesapp.feature_recipe_online.domain.repository

import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipe
import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApiRepository {
    @GET("/recipes")
    suspend fun getRecipesSortByNameAndOrder(
        @Query("sortBy") sortBy: String,
        @Query("order") order: String
    ): Response<RemoteRecipesResponse>
}