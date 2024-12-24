package com.project.recipesapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.recipesapp.feature_recipe.presentation.add_edit_recipe.AddEditScreen
import com.project.recipesapp.feature_recipe.presentation.recipes.RecipesScreen
import com.project.recipesapp.feature_recipe.presentation.util.Screen

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.RecipesScreen.route,
        modifier = modifier
    ) {
        composable(route = Screen.RecipesScreen.route) {
            RecipesScreen(navController = navController)
        }

        composable(
            route = Screen.AddEditRecipeScreen.route + "?recipeId={recipeId}&recipeColor={recipeColor}",
            arguments = listOf(
                navArgument(
                    name = "recipeId"
                ) {
                    type = NavType.LongType
                    defaultValue = -1L
                },
                navArgument(
                    name = "recipeColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditScreen(
                navController = navController,
                recipeColor = it.arguments?.getInt("recipeColor") ?: -1
            )
        }
    }
}