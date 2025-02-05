package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipe
import com.project.recipesapp.feature_recipe_online.domain.util.OrderType

sealed class SuggestedRecipesEvent {
    data class Order(val orderType: OrderType) : SuggestedRecipesEvent()
    data class SaveRecipe(val recipe: RemoteRecipe) : SuggestedRecipesEvent()
}