package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipesapp.feature_recipe_online.domain.use_case.GetRecipesApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestedRecipesViewModel @Inject constructor(
    private val getRecipesApiUseCase: GetRecipesApiUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RemoteRecipesState())
    val state = _state.asStateFlow()

    private val _countRecipe = MutableStateFlow(2)
    val countRecipe = _countRecipe.asStateFlow()

    private var getRecipesJob: Job? = null

    init {
        getRecipes("1")
    }

    fun getRecipes(recipe: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(recipes = getRecipesApiUseCase(recipe))
        }
    }

    fun onCountRecipe() {
        if (_countRecipe.value == 20) {
            _countRecipe.value = 0
        }
        _countRecipe.value += 1
    }

}