package com.project.recipesapp.feature_recipe.domain.use_case

data class RecipeUseCases(
    val getRecipesUseCase: GetRecipesUseCase,
    val deleteRecipeUseCase: DeleteRecipeUseCase,
    val addRecipeUseCase: AddRecipeUseCase,
    val getRecipeUseCase: GetRecipeUseCase
)
