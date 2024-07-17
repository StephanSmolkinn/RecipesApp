package com.project.recipesapp.feature_recipe.presentation.recipes

import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.util.RecipeOrder

sealed class RecipesEvent {

    data class Order(val recipeOrder: RecipeOrder) : RecipesEvent()

    data class DeleteRecipe(val recipe: Recipe) : RecipesEvent()

    object RestoreRecipe : RecipesEvent()

    object ToggleOrderSection : RecipesEvent()

}
