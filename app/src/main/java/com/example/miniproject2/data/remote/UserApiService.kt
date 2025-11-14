package com.example.miniproject2.data.remote

import retrofit2.http.GET

interface UserApiService{

    @GET("users")
    suspend fun getUsers(): List<UserApiModel>

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}