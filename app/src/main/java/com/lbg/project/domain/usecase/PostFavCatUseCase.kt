package com.lbg.project.domain.usecase

import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.catDetails.PostFavCatModel
import com.lbg.project.data.models.mappers.CallSuccessModel
import com.lbg.project.domain.repositories.CatDetailsRepository
import com.lbg.project.utils.Constants
import kotlinx.coroutines.flow.Flow

class PostFavCatUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId: String): Flow<NetworkResult<CallSuccessModel>> {
        val favCat = PostFavCatModel(imageId, Constants.SUB_ID)
        return catDetailsRepo.postFavouriteCat(favCat)
    }
}

