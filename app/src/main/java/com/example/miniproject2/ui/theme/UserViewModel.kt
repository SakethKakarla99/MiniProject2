package com.example.miniproject2.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniproject2.data.UserRepository
import com.example.miniproject2.data.local.UserEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UserUiState(
    val users: List<UserEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    // UI observes only this state
    val uiState: StateFlow<UserUiState> =
        combine(
            _searchQuery,
            _loading,
            _error,
            _searchQuery.flatMapLatest { query ->
                repository.getUsersFlow(query)
            }
        ) { query, loading, error, users ->
            UserUiState(
                users = users,
                isLoading = loading,
                errorMessage = error,
                searchQuery = query
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserUiState()
        )

    init {
        // Offline-first logic:
        // 1. Show cached Room data immediately
        // 2. Try fetching fresh API data in background
        refreshFromRemote()
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun refreshFromRemote() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                repository.refreshUsers()
            } catch (e: Exception) {
                _error.value = "Failed to fetch latest users (offline?)."
            } finally {
                _loading.value = false
            }
        }
    }
}


