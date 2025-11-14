package com.example.miniproject2.ui.theme

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.miniproject2.data.UserRepository
import com.example.miniproject2.data.local.UserDatabase
import com.example.miniproject2.data.remote.RetrofitClient

class UserViewModelFactory(
    private val appContext: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val db = UserDatabase.getInstance(appContext)

        val repo = UserRepository(
            api = RetrofitClient.api,
            userDao = db.userDao()
        )

        return UserViewModel(repo) as T
    }
}


