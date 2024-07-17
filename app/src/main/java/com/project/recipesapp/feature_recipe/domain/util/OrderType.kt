package com.project.recipesapp.feature_recipe.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object  Descending : OrderType()
}