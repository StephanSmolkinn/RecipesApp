package com.project.recipesapp.feature_recipe.domain.use_case

data class RecipeUseCases(
    val getRecipesUseCase: GetRecipesUseCase,
    val deleteRecipeUseCase: DeleteRecipeUseCase,
    val addRecipe: AddRecipe,
    val getRecipeUseCase: GetRecipeUseCase
)
