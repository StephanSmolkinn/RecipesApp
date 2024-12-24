package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipe
import com.project.recipesapp.feature_recipe_online.domain.util.OrderType

data class RemoteRecipesState(
    val recipes: List<RemoteRecipe> = emptyList(),
    val orderType: OrderType = OrderType.Desc()
)