package com.example.miniproject2.data

import com.example.miniproject2.data.local.UserDao
import com.example.miniproject2.data.local.UserEntity
import com.example.miniproject2.data.remote.UserApiService
import kotlinx.coroutines.flow.Flow
import android.util.Log

class UserRepository(
    private val api: UserApiService,
    private val userDao: UserDao
) {

    // UI always reads from Room
    fun getUsersFlow(searchQuery: String): Flow<List<UserEntity>> {
        return if (searchQuery.isBlank()) {
            userDao.getAllUsers()
        } else {
            userDao.searchUsers(searchQuery)
        }
    }

    // Offline-first:
    // Try fetching from API → if success, update DB
    suspend fun refreshUsers() {
        try {
            val remoteUsers = api.getUsers()
            Log.d("UserRepository", "Fetched ${remoteUsers.size} users from API")
            val entities = remoteUsers.toEntityList()
            userDao.insertUsers(entities)
            Log.d("UserRepository", "Inserted users into Room")
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching users", e)
            throw e  // ViewModel will catch and show the error message
        }
    }
}

