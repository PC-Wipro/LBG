package com.lbg.project.domain.repositories


import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.SuccessResponse
import com.lbg.project.data.models.catDetails.PostFavCatModel
import kotlinx.coroutines.flow.Flow

interface CatDetailsRepository {
    suspend fun postFavouriteCat(favCat: PostFavCatModel): Flow<NetworkResult<SuccessResponse>>
    suspend fun deleteFavouriteCat(
        imageId: String,
        favouriteId: Int
    ): Flow<NetworkResult<SuccessResponse>>

    suspend fun isFavourite(imageId: String): Flow<Int?>

}