package com.onedev.reqres.network

import com.onedev.reqres.data.UserDetailResponse
import com.onedev.reqres.data.UserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET(EndPoint.GET_USER)
    suspend fun getList(
        @Query("page") page: Int
    ): Response<UserResponse>

    @GET(EndPoint.GET_USER)
    suspend fun getDetail(
        @Path("id") id: String
    ): Response<UserDetailResponse>

    companion object {
        private const val BASE_URL = "https://reqres.in/"
        fun build(): WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }

    object EndPoint {
        const val GET_USER = "api/users"
        const val GET_USER_DETAIL = "api/users/{id}"
    }
}