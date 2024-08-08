package com.project.recipesapp.feature_recipe_online.domain.use_case

import android.util.Log
import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipe
import com.project.recipesapp.feature_recipe_online.domain.repository.RecipeApiRepository
import retrofit2.HttpException
import java.io.IOException

class GetRecipesApiUseCase(
    private val recipeApiRepository: RecipeApiRepository
) {
    suspend operator fun invoke(recipe: String) : RemoteRecipe? {
        val response =
            try {
                recipeApiRepository.getRecipes(recipe)
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
