package com.project.recipesapp.feature_recipe.presentation.recipes

import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.util.OrderType
import com.project.recipesapp.feature_recipe.domain.util.RecipeOrder

data class RecipesState(
    val recipes: List<Recipe> = emptyList(),
    val recipeOrder: RecipeOrder = RecipeOrder.Date(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false
)
