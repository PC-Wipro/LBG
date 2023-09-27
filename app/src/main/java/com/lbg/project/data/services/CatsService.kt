package com.lbg.project.data.services


import com.lbg.project.models.CatsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsService {


    @GET("v1/images/search")
    suspend fun fetchCatsImages(
        @Query("limit") limit: Int
    ): Response<List<CatsDataModel>>
}