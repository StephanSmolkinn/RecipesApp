package com.project.recipesapp.feature_recipe.presentation.add_edit_recipe

import androidx.compose.ui.focus.FocusState

sealed class AddEditRecipeEvent {

    data class SetTitle(val title: String) : AddEditRecipeEvent()

    data class ChangeTitleFocus(val focus: FocusState) : AddEditRecipeEvent()

    data class SetIngredients(val ingredients: String) : AddEditRecipeEvent()

    data class ChangeIngredientsFocus(val focus: FocusState) : AddEditRecipeEvent()

    data class SetServings(val servings: String) : AddEditRecipeEvent()

    data class ChangeServingsFocus(val focus: FocusState) : AddEditRecipeEvent()

    data class SetInstruction(val instruction: String) : AddEditRecipeEvent()

    data class ChangeInstructionFocus(val focus: FocusState) : AddEditRecipeEvent()

    data class SetColor(val color: Int) : AddEditRecipeEvent()

    object AddRecipe : AddEditRecipeEvent()

}