package com.project.recipesapp.feature_recipe.presentation.add_edit_recipe

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.presentation.add_edit_recipe.components.TransparentTextFieldHint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditScreen(
    navController: NavController,
    recipeColor: Int,
    viewModel: AddEditRecipeViewModel = hiltViewModel()
) {

    val titleState = viewModel.stateTitle.value
    val ingredientsState = viewModel.stateIngredients.value
    val servingsState = viewModel.stateServings.value
    val instructionState = viewModel.stateInstructions.value

    val scaffoldState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val animateBackGroundColor = remember {
        Animatable(
            Color(if (recipeColor != -1) recipeColor else viewModel.stateColor.value)
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.stateFlow.collectLatest { event ->
            when(event) {
                AddEditRecipeViewModel.UiEvent.AddRecipe -> {
                    navController.navigateUp()
                }
                is AddEditRecipeViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditRecipeEvent.AddRecipe) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Add recipe"
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = scaffoldState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(animateBackGroundColor.value)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Recipe.recipeColor.forEach { color ->
                    val intColor = color.toArgb()

                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.stateColor.value == intColor)
                                    Color.Black
                                else
                                    Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    animateBackGroundColor.animateTo(
                                        targetValue = Color(intColor),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditRecipeEvent.SetColor(intColor))
                            }
                    ) {

                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextFieldHint(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = { viewModel.onEvent(AddEditRecipeEvent.SetTitle(it)) },
                onFocusChange = { viewModel.onEvent(AddEditRecipeEvent.ChangeTitleFocus(it)) },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextFieldHint(
                text = "Ingredients: ${ingredientsState.text}",
                hint = ingredientsState.hint,
                onValueChange = { viewModel.onEvent(AddEditRecipeEvent.SetIngredients(it)) },
                onFocusChange = { viewModel.onEvent(AddEditRecipeEvent.ChangeIngredientsFocus(it)) },
                isHintVisible = ingredientsState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextFieldHint(
                text = "Servings: ${servingsState.text}",
                hint = servingsState.hint,
                onValueChange = { viewModel.onEvent(AddEditRecipeEvent.SetServings(it)) },
                onFocusChange = { viewModel.onEvent(AddEditRecipeEvent.ChangeServingsFocus(it)) },
                isHintVisible = servingsState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextFieldHint(
                text = instructionState.text,
                hint = instructionState.hint,
                onValueChange = { viewModel.onEvent(AddEditRecipeEvent.SetInstruction(it)) },
                onFocusChange = { viewModel.onEvent(AddEditRecipeEvent.ChangeInstructionFocus(it)) },
                isHintVisible = instructionState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
            )
        }
    }
    
}