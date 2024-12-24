package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipesapp.feature_recipe_online.domain.use_case.GetRecipesSortedByNameAndOrderUseCase
import com.project.recipesapp.feature_recipe_online.domain.use_case.SuggestedRecipesUseCases
import com.project.recipesapp.feature_recipe_online.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestedRecipesViewModel @Inject constructor(
    private val suggestedRecipesUseCases: SuggestedRecipesUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(RemoteRecipesState())
    val state = _state.asStateFlow()

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
        }
    }

    private fun getRecipes(orderType: OrderType) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipes = suggestedRecipesUseCases.getRecipesSortedByNameAndOrderUseCase(orderType.name)?.recipes
            _state.value = _state.value.copy(recipes = recipes ?: emptyList())
        }
    }

}