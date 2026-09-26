package com.example.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.rounded.Assignment
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME("Home", Icons.Rounded.Home, Icons.Outlined.Home),
    PLAN("Plan", Icons.Rounded.CalendarMonth, Icons.Outlined.CalendarMonth),
    FINANCES("Finanse", Icons.Rounded.AccountBalanceWallet, Icons.Outlined.AccountBalanceWallet),
    AFFAIRS("Sprawy", Icons.AutoMirrored.Rounded.Assignment, Icons.AutoMirrored.Outlined.Assignment),
    PROFILE("Profil", Icons.Rounded.Person, Icons.Outlined.Person)
}
