package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipesapp.feature_recipe.domain.model.InvalidRecipeException
import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.use_case.GetRecipesUseCase
import com.project.recipesapp.feature_recipe.domain.use_case.RecipeUseCases
import com.project.recipesapp.feature_recipe_online.domain.use_case.GetRecipesSortedByNameAndOrderUseCase
import com.project.recipesapp.feature_recipe_online.domain.use_case.SuggestedRecipesUseCases
import com.project.recipesapp.feature_recipe_online.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestedRecipesViewModel @Inject constructor(
    private val suggestedRecipesUseCases: SuggestedRecipesUseCases,
    private val recipesUseCase: RecipeUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(RemoteRecipesState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getRecipes(_state.value.orderType)
    }

    fun onEvent(event: SuggestedRecipesEvent) {
        when (event) {
            is SuggestedRecipesEvent.Order -> {
                _state.update {
                    it.copy(
                        orderType = event.orderType
                    )
                }

                getRecipes(state.value.orderType)
            }

            is SuggestedRecipesEvent.SaveRecipe -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        recipesUseCase.addRecipeUseCase(
                            Recipe(
                                title = event.recipe.name,
                                ingredients = event.recipe.ingredients.toString(),
                                servings = event.recipe.servings.toString(),
                                instructions = event.recipe.instructions.toString(),
                                timestamp = System.currentTimeMillis(),
                                color = Recipe.recipeColor.random().toArgb(),
                                id = null
                            )
                        )
                        _eventFlow.emit(UiEvent.ShowSnackBar(message = "Recipe saved"))
                    } catch (e: InvalidRecipeException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar(message = "Can not save recipe"))
                    }
                }
            }
        }
    }

    private fun getRecipes(orderType: OrderType) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipes = suggestedRecipesUseCases.getRecipesSortedByNameAndOrderUseCase(orderType.name)?.recipes
            _state.value = _state.value.copy(recipes = recipes ?: emptyList())
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
    }

}