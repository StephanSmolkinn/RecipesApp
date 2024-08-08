package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import com.project.recipesapp.feature_recipe_online.domain.model.RemoteRecipe

data class RemoteRecipesState(
    val recipes: RemoteRecipe? = null,
)