package com.project.recipesapp.feature_recipe.domain.use_case

import com.project.recipesapp.feature_recipe.domain.model.InvalidRecipeException
import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.repository.RecipeRepository

class AddRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    @Throws(InvalidRecipeException::class)
    suspend operator fun invoke(recipe: Recipe) {
        if (recipe.title.isEmpty() || recipe.instructions.isEmpty())
            throw InvalidRecipeException("Title or instruction are empty")

        recipeRepository.insertRecipe(recipe)
    }
}