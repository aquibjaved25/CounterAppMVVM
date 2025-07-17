package com.assignment4

import retrofit2.http.GET
import retrofit2.http.Query

interface PicSumApi {

    @GET("v2/list")
    suspend fun searchRepositories(
        @Query("page") pageNumber: Int,
        @Query("limit") pageSize: Int
    ): List<RepositoryData>

}