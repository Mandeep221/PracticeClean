package com.example.practice

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dashboard.ui.composable.DashboardContent
import com.example.dashboard.ui.composable.DashboardScreen
import com.example.dashboard.ui.composable.ScoreLogicScreen
import com.example.dashboard.ui.model.User
import com.example.dashboard.ui.viewmodel.DashboardViewModel
import com.example.practice.navigation.Screen
import com.example.practice.ui.theme.PracticeTheme
import com.example.profile.composable.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

                val snackBarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()

                Scaffold(
                    topBar = {
                        AppBar(
                            title = currentScreen,
                            canGoBack = canGoBack,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    },
                    snackbarHost = { SnackbarHost(snackBarHostState) }
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
                                    // also show snackbar
                                    coroutineScope.launch {
                                        snackBarHostState.showSnackbar(
                                            message = "Item clicked",
                                            actionLabel = "Dismiss",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
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

        // Flatten nested streams
        val allPolicies = getEmployees().flatMap { it.policies }
        Log.d("Policies", "$allPolicies")
    }
}

fun getEmployees() = listOf(
    Employee(
        name = "Manu",
        policies = listOf("Life", "Auto")
    ),
    Employee(
        name = "Nanu",
        policies = listOf("Home", "Boat")
    ),
    Employee(
        name = "Osheen",
        policies = listOf("Villa", "Shop")
    )
)

data class Employee(
    val name: String,
    val policies: List<String>
)

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

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DashboardPreview() {
    PracticeTheme {
        DashboardContent(
            students = emptyList(),
            list = listOf(
                User(id = "", name = "Manu"),
                User(id = "", name = "Nanu"),
                User(id = "", name = "John"),
            ),
            onClick = {},
            onAddUser = {}
        )
    }
}