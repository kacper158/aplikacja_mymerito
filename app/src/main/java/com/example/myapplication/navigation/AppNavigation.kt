package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.ui.screens.login.LoginScreen
import com.example.myapplication.ui.screens.main.MainContainer

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val backStack = remember { mutableStateListOf<NavRoute>(NavRoute.Login) }

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.size - 1)
            }
        },
        entryProvider = { key ->
            when (key) {
                is NavRoute.Login -> NavEntry(key = key) {
                    LoginScreen(
                        onLoginSuccess = {
                            backStack.clear()
                            backStack.add(NavRoute.Main)
                        }
                    )
                }
                is NavRoute.Main -> NavEntry(key = key) {
                    MainContainer(
                        onLogout = {
                            backStack.clear()
                            backStack.add(NavRoute.Login)
                        }
                    )
                }
            }
        }
    )
}
