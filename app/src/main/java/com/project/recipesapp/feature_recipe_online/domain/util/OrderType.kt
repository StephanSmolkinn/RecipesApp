package com.project.recipesapp.feature_recipe_online.domain.util

sealed class OrderType(val name: String) {
    data class Desc(val name1: String = "desc") : OrderType(name = name1)
    data class Asc(val name1: String = "asc") : OrderType(name = name1)
}