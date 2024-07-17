package com.project.recipesapp.feature_recipe.domain.util

sealed class RecipeOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : RecipeOrder(orderType)
    class Date(orderType: OrderType) : RecipeOrder(orderType)
    class Color(orderType: OrderType) : RecipeOrder(orderType)

    fun changeOrderType(orderType: OrderType) : RecipeOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}