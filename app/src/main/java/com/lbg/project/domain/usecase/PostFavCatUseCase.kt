package com.lbg.project.domain.usecase

import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.catDetails.PostFavCatModel
import com.lbg.project.domain.mappers.CallSuccessModel
import com.lbg.project.domain.mappers.mapSuccessData
import com.lbg.project.domain.repositories.CatDetailsRepository
import com.lbg.project.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostFavCatUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId: String): Flow<NetworkResult<CallSuccessModel>> = flow {
        val favCat = PostFavCatModel(imageId, Constants.SUB_ID)
        catDetailsRepo.postFavouriteCat(favCat).collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    emit(NetworkResult.Success(response.data?.mapSuccessData()))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(response.message))
                }

                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}

