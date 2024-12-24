package com.project.recipesapp.feature_recipe_online.domain.use_case

import android.util.Log
import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipesResponse
import com.project.recipesapp.feature_recipe_online.domain.repository.RecipeApiRepository
import com.project.recipesapp.feature_recipe_online.domain.util.OrderType
import retrofit2.HttpException
import java.io.IOException

class GetRecipesSortedByNameAndOrderUseCase(
    private val recipeApiRepository: RecipeApiRepository
) {
    suspend operator fun invoke(order: String) : RemoteRecipesResponse? {
        val response =
            try {
                recipeApiRepository.getRecipesSortByNameAndOrder("name", order)
            } catch (e: IOException) {
                Log.e("MainActivity", "IOException")
                return null
            } catch (e: HttpException) {
                Log.e("MainActivity", "HttpException")
                return null
            }

        if (response.isSuccessful && response.body() != null) {
            return response.body()
        }

        return null
    }
}