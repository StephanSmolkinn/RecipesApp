package com.project.recipesapp.feature_recipe.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.recipesapp.ui.theme.Blue
import com.project.recipesapp.ui.theme.Pink
import com.project.recipesapp.ui.theme.Pink80
import com.project.recipesapp.ui.theme.Red
import com.project.recipesapp.ui.theme.Violet

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val ingredients: String,
    val servings: String,
    val instructions: String,
    val color: Int
) {
    companion object {
        val recipeColor = listOf(Violet, Pink, Blue, Red, Pink80)
    }
}
