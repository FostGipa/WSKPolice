package com.example.wspolicy.retrofit

import com.example.wspolicy.models.Departments
import com.example.wspolicy.models.UserData
import com.example.wspolicy.models.Wanteds
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/login/")
    fun login(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<UserData>

    @GET("/api/department")
    suspend fun departments(): Response<Departments>

    @GET("/api/wanted")
    suspend fun wanteds(): Response<Wanteds>
}