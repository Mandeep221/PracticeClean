package com.example.dashboard.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dashboard.domain.model.UserEntity
import com.example.dashboard.ui.model.User
import com.example.dashboard.ui.viewmodel.DashboardViewModel
import com.example.dashboard.ui.viewmodel.UiState

@Composable
fun DashboardScreen(
    onClick: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state = viewModel.tripsState.collectAsStateWithLifecycle()
    val students by viewModel.students.collectAsStateWithLifecycle()

    when (val value = state.value) {
        is UiState.Loading -> {
            DashboardLoading()
        }

        is UiState.Success -> {
            DashboardContent(
                students = students,
                list = value.data,
                onClick = onClick,
                onAddUser = { name -> viewModel.addUser(name) }
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
    students: List<UserEntity>, // technically should be a ui model
    list: List<User>,
    onClick: () -> Unit,
    onAddUser: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
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

        Spacer(modifier = Modifier.size(32.dp))
        Text(text = students.joinToString(", ") { it.name })
        students.map {it.name}.joinToString { ", " }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            var name by remember { mutableStateOf("") }

            OutlinedTextField(
                value = name,
                onValueChange = { input ->
                    if (input.all { it.isLetter() }) {
                        name = input
                    }
                },
                label = { Text(text = "Enter name") }
            )

            Button(onClick = {
                onAddUser(name)
            }) {
                Text("Add User")
            }
        }
        Spacer(modifier = Modifier.size(32.dp))
    }
}