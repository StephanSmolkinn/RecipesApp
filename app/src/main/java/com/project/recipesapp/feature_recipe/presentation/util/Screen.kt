package com.project.recipesapp.feature_recipe.presentation.util

sealed class Screen(val route: String) {
    object RecipesScreen : Screen("recipes_screen")
    object AddEditRecipeScreen : Screen("add_edit_recipes_screen")
}