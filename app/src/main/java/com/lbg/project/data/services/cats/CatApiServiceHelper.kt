package com.lbg.project.data.services.cats

import com.lbg.project.data.models.catData.CatResponse
import com.lbg.project.data.models.catData.FavouriteCatsItem
import retrofit2.Response

interface CatApiServiceHelper {
    suspend fun fetchCatsImages(limit:Int): Response<List<CatResponse>>
    suspend fun fetchFavouriteCats(subId:String): Response<List<FavouriteCatsItem>>

}