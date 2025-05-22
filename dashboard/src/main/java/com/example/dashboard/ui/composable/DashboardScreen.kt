package com.example.dashboard.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dashboard.ui.model.User
import com.example.dashboard.ui.viewmodel.DashboardViewModel
import com.example.dashboard.ui.viewmodel.UiState

@Composable
fun DashboardScreen(
    onClick: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state = viewModel.tripsState.collectAsStateWithLifecycle()

    when (val value = state.value) {
        is UiState.Loading -> {
            DashboardLoading()
        }

        is UiState.Success -> {
            DashboardContent(
                list = value.data,
                onClick = onClick
            )
        }

        is UiState.Error -> {
            DashboardError(value.error)
        }
    }
}

@Composable
fun DashboardError(error: String) {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = error
    )
}

@Composable
fun DashboardLoading() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Loading"
    )
}

@Composable
private fun DashboardContent(
    modifier: Modifier = Modifier,
    list: List<User>,
    onClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = spacedBy(32.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(list.size) {
            Text(
                modifier = modifier.clickable { onClick() },
                text = list[it].name,
            )
        }
    }
}