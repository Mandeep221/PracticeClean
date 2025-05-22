package com.example.dashboard.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard.domain.AddStudentUseCase
import com.example.dashboard.domain.DomainResult
import com.example.dashboard.domain.GetDashboardStuffUseCase
import com.example.dashboard.domain.GetStudentsUseCase
import com.example.dashboard.domain.model.UserEntity
import com.example.dashboard.ui.model.User
import com.example.dashboard.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardStuffUseCase: GetDashboardStuffUseCase,
    private val addStudentUseCase: AddStudentUseCase,
    private val getStudentsUseCase: GetStudentsUseCase
) : ViewModel() {

    private val _tripsState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val tripsState = _tripsState.asStateFlow()

    val students: StateFlow<List<UserEntity>> = getStudentsUseCase.execute()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            when (val result = getDashboardStuffUseCase.execute()) {
                is DomainResult.Success -> {
                    val users = result.data.map { it.toUiModel() }
                    _tripsState.update {
                        UiState.Success(users)
                    }
                }

                is DomainResult.Failure -> {
                    _tripsState.update {
                        UiState.Error("Something went wrong")
                    }
                }
            }
        }
    }

    fun addUser(name: String) {
        viewModelScope.launch {
            addStudentUseCase.execute(name)
//            delay(2000)
//            getStudentsUseCase.execute().collectLatest { students ->
//                students.forEach { student ->
//                    Log.d("StudentName", student.name)
//                }
//            }
        }
    }
}

sealed class UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val error: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}

