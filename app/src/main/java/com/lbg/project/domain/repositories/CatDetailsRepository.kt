package com.lbg.project.domain.repositories


import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.catDetails.PostFavCatModel
import com.lbg.project.data.models.mappers.CallSuccessModel
import kotlinx.coroutines.flow.Flow

interface CatDetailsRepository {
    suspend fun postFavouriteCat(favCat: PostFavCatModel): Flow<NetworkResult<CallSuccessModel>>
    suspend fun deleteFavouriteCat(
        imageId: String,
        favouriteId: Int
    ): Flow<NetworkResult<CallSuccessModel>>

    suspend fun isFavourite(imageId: String): Flow<Int?>

}