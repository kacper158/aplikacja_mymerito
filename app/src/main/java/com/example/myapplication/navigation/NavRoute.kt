package com.example.myapplication.navigation

import kotlinx.serialization.Serializable

sealed interface NavRoute {
    @Serializable
    data object Login : NavRoute

    @Serializable
    data object Main : NavRoute
}
