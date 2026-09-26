package com.example.myapplication.ui.screens.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.navigation.BottomTab
import com.example.myapplication.ui.components.BottomNavigationBar
import com.example.myapplication.ui.screens.affairs.AffairsScreen
import com.example.myapplication.ui.screens.finance.FinanceScreen
import com.example.myapplication.ui.screens.home.HomeScreen
import com.example.myapplication.ui.screens.profile.ProfileScreen
import com.example.myapplication.ui.screens.schedule.ScheduleScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun MainContainer(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentTab by remember { mutableStateOf(BottomTab.HOME) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                currentTab = currentTab,
                onTabSelected = { newTab -> currentTab = newTab }
            )
        }
    ) { innerPadding ->
        Crossfade(
            targetState = currentTab,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            label = "TabCrossfade"
        ) { tab ->
            when (tab) {
                BottomTab.HOME -> HomeScreen(onNavigateToTab = { currentTab = it })
                BottomTab.PLAN -> ScheduleScreen()
                BottomTab.FINANCES -> FinanceScreen()
                BottomTab.AFFAIRS -> AffairsScreen()
                BottomTab.PROFILE -> ProfileScreen(onLogout = onLogout)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContainerPreview() {
    MyApplicationTheme {
        MainContainer(onLogout = {})
    }
}
