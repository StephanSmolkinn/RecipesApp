package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuggestedRecipeEmpty(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No internet\nconnection",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .shadow(5.dp)
            )
        }
    }
}