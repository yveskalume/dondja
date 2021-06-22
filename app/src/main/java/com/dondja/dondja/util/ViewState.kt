package com.dondja.dondja.util

sealed class ViewState<out T> where T: Any? {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failure(val error: Exception) : ViewState<Nothing>()
}
