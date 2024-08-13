package com.project.recipesapp.feature_recipe.presentation.add_edit_recipe

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipesapp.feature_recipe.domain.model.InvalidRecipeException
import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.use_case.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditRecipeViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var recipeId: Long? = null

    private val _stateTitle = mutableStateOf(
        TextFieldState(
            hint = "Set Title"
        )
    )
    val stateTitle: State<TextFieldState> = _stateTitle

    private val _stateIngredients = mutableStateOf(
        TextFieldState(
            hint = "Ingredients"
        )
    )
    val stateIngredients: State<TextFieldState> = _stateIngredients

    private val _stateServings = mutableStateOf(
        TextFieldState(
            hint = "Servings"
        )
    )
    val stateServings: State<TextFieldState> = _stateServings

    private val _stateInstructions = mutableStateOf(
        TextFieldState(
            hint = "Instruction"
        )
    )
    val stateInstructions: State<TextFieldState> = _stateInstructions

    private val _stateColor = mutableStateOf(Recipe.recipeColor.random().toArgb())
    val stateColor: State<Int> = _stateColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val stateFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Long>("recipeId")?.let {
            if (it != -1L) {
                viewModelScope.launch {
                    recipeUseCases.getRecipeUseCase(it)?.also { recipe ->
                        recipeId = recipe.id
                        _stateTitle.value = stateTitle.value.copy(
                            text = recipe.title,
                            isHintVisible = false
                        )
                        _stateIngredients.value = stateIngredients.value.copy(
                            text = recipe.ingredients,
                            isHintVisible = false
                        )
                        _stateServings.value = stateServings.value.copy(
                            text = recipe.servings,
                            isHintVisible = false
                        )
                        _stateInstructions.value = stateInstructions.value.copy(
                            text = recipe.instructions,
                            isHintVisible = false
                        )
                        _stateColor.value = recipe.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditRecipeEvent) {
        when (event) {

            AddEditRecipeEvent.AddRecipe -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        recipeUseCases.addRecipeUseCase(
                            Recipe(
                                title = stateTitle.value.text,
                                ingredients = stateIngredients.value.text,
                                servings = stateServings.value.text,
                                instructions = stateInstructions.value.text,
                                color = stateColor.value,
                                timestamp = System.currentTimeMillis(),
                                id = recipeId
                            )
                        )
                        _eventFlow.emit(UiEvent.AddRecipe)
                    } catch (e: InvalidRecipeException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Can not save recipe"
                            )
                        )
                    }
                }
            }

            is AddEditRecipeEvent.SetColor -> {
                _stateColor.value = event.color
            }

            is AddEditRecipeEvent.ChangeIngredientsFocus -> {
                _stateIngredients.value = stateIngredients.value.copy(
                    isHintVisible = !event.focus.isFocused && stateIngredients.value.text.isEmpty()
                )
            }

            is AddEditRecipeEvent.ChangeInstructionFocus -> {
                _stateInstructions.value = stateInstructions.value.copy(
                    isHintVisible = !event.focus.isFocused && stateInstructions.value.text.isEmpty()
                )
            }

            is AddEditRecipeEvent.ChangeServingsFocus -> {
                _stateServings.value = stateServings.value.copy(
                    isHintVisible = !event.focus.isFocused && stateServings.value.text.isEmpty()
                )
            }

            is AddEditRecipeEvent.ChangeTitleFocus -> {
                _stateTitle.value = stateTitle.value.copy(
                    isHintVisible = !event.focus.isFocused && stateTitle.value.text.isEmpty()
                )
            }

            is AddEditRecipeEvent.SetIngredients -> {
                _stateIngredients.value = stateIngredients.value.copy(
                    text = event.ingredients
                )
            }

            is AddEditRecipeEvent.SetInstruction -> {
                _stateInstructions.value = stateInstructions.value.copy(
                    text = event.instruction
                )
            }

            is AddEditRecipeEvent.SetServings -> {
                _stateServings.value = stateServings.value.copy(
                    text = event.servings
                )
            }

            is AddEditRecipeEvent.SetTitle -> {
                _stateTitle.value = stateTitle.value.copy(
                    text = event.title
                )
            }

        }
    }

    sealed class UiEvent {
        object AddRecipe : UiEvent()
        data class ShowSnackBar(val message: String) : UiEvent()
    }

}