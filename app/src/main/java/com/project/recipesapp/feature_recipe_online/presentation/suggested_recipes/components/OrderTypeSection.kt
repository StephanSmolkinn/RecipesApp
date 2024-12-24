package com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.recipesapp.feature_recipe.presentation.recipes.components.DefaultRadioButton
import com.project.recipesapp.feature_recipe_online.domain.util.OrderType

@Composable
fun OrderTypeSection(
    modifier: Modifier = Modifier,
    orderType: OrderType,
    onChange: (OrderType) -> Unit,
    isOpen: Boolean
) {
    if (isOpen) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                DefaultRadioButton(
                    text = "Descending",
                    selected = orderType is OrderType.Desc,
                    onSelect = { onChange(OrderType.Desc()) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                DefaultRadioButton(
                    text = "Ascending",
                    selected = orderType is OrderType.Asc,
                    onSelect = { onChange(OrderType.Asc()) }
                )
            }
        }
    }
}