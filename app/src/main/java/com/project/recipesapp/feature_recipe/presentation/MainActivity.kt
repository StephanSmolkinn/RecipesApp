package com.project.recipesapp.feature_recipe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.recipesapp.feature_recipe.presentation.add_edit_recipe.AddEditScreen
import com.project.recipesapp.feature_recipe.presentation.recipes.RecipesScreen
import com.project.recipesapp.feature_recipe.presentation.util.Screen
import com.project.recipesapp.ui.theme.RecipesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screen.RecipesScreen.route) {
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
                            AddEditScreen(navController = navController, recipeColor = it.arguments?.getInt("recipeColor") ?: -1)
                        }
                    }
                }
            }
        }
    }
}
