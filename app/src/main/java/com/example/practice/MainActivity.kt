package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dashboard.ui.composable.DashboardScreen
import com.example.dashboard.ui.composable.ScoreLogicScreen
import com.example.practice.navigation.Screen
import com.example.practice.ui.theme.PracticeTheme
import com.example.profile.composable.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        // This will be the Navhost here

        setContent {
            PracticeTheme {
                val navController = rememberNavController()
                val currentBAckStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBAckStackEntry?.destination
                val currentScreen = when (currentDestination?.route) {
                    Screen.Dashboard.route -> "Dashboard"
                    Screen.Profile.route -> "Profile"
                    else -> "ScoreLogic"
                }
                val canGoBack = navController.previousBackStackEntry != null

                Scaffold(
                    topBar = {
                        AppBar(
                            title = currentScreen,
                            canGoBack = canGoBack,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Dashboard.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Dashboard.route) {
                            DashboardScreen(
                                onClick = {
                                    navController.navigate(Screen.Profile.route)
                                }
                            )
                        }
                        composable(Screen.Profile.route) {
                            ProfileScreen(
                                onClick = {
                                    navController.navigate(Screen.ScoreLogic.route)
                                }
                            )
                        }
                        composable(Screen.ScoreLogic.route) {
                            ScoreLogicScreen()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    title: String,
    canGoBack: Boolean = true,
    onBackClick: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { Text(text = title) },
        navigationIcon = {
            if (canGoBack) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "back navigation"
                    )
                }
            }
        }
    )
}