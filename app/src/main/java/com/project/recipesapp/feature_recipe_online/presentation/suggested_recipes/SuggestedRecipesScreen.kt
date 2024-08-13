package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asLiveData
import com.project.recipesapp.feature_recipe.presentation.recipes.RecipesEvent
import com.project.recipesapp.feature_recipe.presentation.util.Screen
import com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes.components.SuggestedRecipeEmpty
import com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes.components.SuggestedRecipeItem

@Composable
fun SuggestedRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SuggestedRecipesViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsState()
    val countRecipe = viewModel.countRecipe.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Suggested Recipes",
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(
                    onClick = {
                        viewModel.getRecipes(countRecipe.value.toString())
                        viewModel.onCountRecipe()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Open Order Section"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (state.value.recipes != null) {
                SuggestedRecipeItem(
                    remoteRecipe = state.value.recipes!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            } else {
                SuggestedRecipeEmpty(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }

}