package com.project.recipesapp.feature_recipe.domain.use_case

import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.repository.RecipeRepository
import com.project.recipesapp.feature_recipe.domain.util.OrderType
import com.project.recipesapp.feature_recipe.domain.util.RecipeOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecipesUseCase(
    private val recipeRepository: RecipeRepository
) {
    operator fun invoke(
        recipeOrder: RecipeOrder = RecipeOrder.Date(orderType = OrderType.Ascending)
    ): Flow<List<Recipe>> {

        return recipeRepository.getRecipes().map { recipes ->
            when(recipeOrder.orderType) {
                is OrderType.Ascending -> {
                    when(recipeOrder) {
                        is RecipeOrder.Color -> recipes.sortedBy { it.color }
                        is RecipeOrder.Date -> recipes.sortedBy { it.timestamp }
                        is RecipeOrder.Title -> recipes.sortedBy { it.title.lowercase() }
                    }
                }
                is OrderType.Descending -> {
                    when(recipeOrder) {
                        is RecipeOrder.Color -> recipes.sortedByDescending { it.color }
                        is RecipeOrder.Date -> recipes.sortedByDescending { it.timestamp }
                        is RecipeOrder.Title -> recipes.sortedByDescending { it.title.lowercase() }
                    }
                }
            }
        }

    }
}