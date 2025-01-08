package com.project.recipesapp.feature_recipe_online.domain.use_case

import android.util.Log
import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipesResponse
import com.project.recipesapp.feature_recipe_online.domain.repository.RecipeApiRepository
import com.project.recipesapp.feature_recipe_online.domain.util.OrderType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetRecipesSortedByNameAndOrderUseCase(
    private val recipeApiRepository: RecipeApiRepository
) {
    operator fun invoke(order: String) : Flow<RemoteRecipesResponse> = flow {
        val response = recipeApiRepository.getRecipesSortByNameAndOrder("name", order)
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        }
    }.catch { exception ->
        throw exception
    }.flowOn(Dispatchers.IO)
}