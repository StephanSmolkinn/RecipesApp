package com.project.recipesapp.feature_recipe.domain.use_case

import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.repository.RecipeRepository

class DeleteRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(recipe: Recipe) = recipeRepository.deleteRecipe(recipe)
}