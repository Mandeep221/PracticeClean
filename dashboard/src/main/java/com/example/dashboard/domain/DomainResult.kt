package com.example.dashboard.domain

sealed class DomainResult<out T> {
    data class Success<out T>(val data: T): DomainResult<T>()
    data class Failure(val error: Throwable): DomainResult<Nothing>()
}