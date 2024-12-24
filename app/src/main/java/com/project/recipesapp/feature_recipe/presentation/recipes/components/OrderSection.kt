package com.project.recipesapp.feature_recipe.presentation.recipes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.recipesapp.feature_recipe.domain.util.OrderType
import com.project.recipesapp.feature_recipe.domain.util.RecipeOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    order: RecipeOrder = RecipeOrder.Date(OrderType.Descending),
    onChange: (RecipeOrder) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Date",
                selected = order is RecipeOrder.Date,
                onSelect = { onChange(RecipeOrder.Date(order.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Title",
                selected = order is RecipeOrder.Title,
                onSelect = { onChange(RecipeOrder.Title(order.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = order is RecipeOrder.Color,
                onSelect = { onChange(RecipeOrder.Color(order.orderType)) }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Descending",
                selected = order.orderType is OrderType.Descending,
                onSelect = { onChange(order.changeOrderType(OrderType.Descending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Ascending",
                selected = order.orderType is OrderType.Ascending,
                onSelect = { onChange(order.changeOrderType(OrderType.Ascending)) }
            )
        }
    }
}