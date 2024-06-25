package com.project.recipesapp.feature_recipe.data.repository

import com.project.recipesapp.feature_recipe.data.data_source.RecipeDao
import com.project.recipesapp.feature_recipe.domain.model.Recipe
import com.project.recipesapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override fun getRecipes(): Flow<List<Recipe>> = recipeDao.getRecipes()

    override suspend fun getRecipeById(id: Long): Recipe? = recipeDao.getRecipeById(id)

    override suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe)

    override suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe)

}