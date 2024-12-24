package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import com.project.recipesapp.feature_recipe_online.domain.util.OrderType

sealed class SuggestedRecipesEvent {
    data class Order(val orderType: OrderType) : SuggestedRecipesEvent()
}