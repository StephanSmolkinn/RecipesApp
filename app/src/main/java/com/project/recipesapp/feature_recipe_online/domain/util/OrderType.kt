package com.project.recipesapp.feature_recipe_online.domain.util

sealed class OrderType(val name: String) {
    class Desc(name: String = "desc") : OrderType(name = name)
    class Asc(name: String = "asc") : OrderType(name = name)
}