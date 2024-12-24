package com.project.recipesapp.feature_recipe.presentation.recipes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.use_case.RecipeUseCases
import com.project.recipesapp.feature_recipe.domain.util.OrderType
import com.project.recipesapp.feature_recipe.domain.util.RecipeOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases
) : ViewModel() {

    private val _state = mutableStateOf(RecipesState())
    val state: State<RecipesState> = _state

    private var deletedRecipe: Recipe? = null

    private var getRecipesJob: Job? = null

    init {
        getRecipes(RecipeOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: RecipesEvent) {
        when (event) {

            is RecipesEvent.DeleteRecipe -> {
                viewModelScope.launch(Dispatchers.IO) {
                    recipeUseCases.deleteRecipeUseCase(event.recipe)
                    deletedRecipe = event.recipe
                }
            }

            is RecipesEvent.Order -> {
                if (state.value.recipeOrder::class == event.recipeOrder::class &&
                    state.value.recipeOrder.orderType == event.recipeOrder.orderType
                ) {
                    return
                }
                getRecipes(event.recipeOrder)
            }

            RecipesEvent.RestoreRecipe -> {
                viewModelScope.launch(Dispatchers.IO) {
                    deletedRecipe?.let {
                        recipeUseCases.addRecipeUseCase(it)
                    }
                    deletedRecipe = null
                }
            }

            RecipesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

        }
    }

    private fun getRecipes(recipeOrder: RecipeOrder) {
        getRecipesJob?.cancel()

        getRecipesJob = recipeUseCases.getRecipesUseCase(recipeOrder).onEach {
            _state.value = state.value.copy(
                recipes = it,
                recipeOrder = recipeOrder
            )
        }.launchIn(viewModelScope)
    }

}