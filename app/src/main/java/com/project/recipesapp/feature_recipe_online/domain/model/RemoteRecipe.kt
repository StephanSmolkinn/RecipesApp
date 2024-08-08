package com.project.recipesapp.feature_recipe_online.domain.model

import com.project.recipesapp.ui.theme.Blue
import com.project.recipesapp.ui.theme.Pink
import com.project.recipesapp.ui.theme.Pink80
import com.project.recipesapp.ui.theme.Red
import com.project.recipesapp.ui.theme.Violet

data class RemoteRecipe(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val name: String,
    val prepTimeMinutes: Int,
    val rating: Double,
    val reviewCount: Int,
    val servings: Int,
    val tags: List<String>,
    val userId: Int
) {
    companion object {
        val colors = listOf(Violet, Pink, Blue, Red, Pink80)
    }
}