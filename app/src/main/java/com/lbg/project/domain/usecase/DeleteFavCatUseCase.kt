package com.lbg.project.domain.usecase

import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.mappers.CallSuccessModel
import com.lbg.project.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow

class DeleteFavCatUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId: String, favId: Int): Flow<NetworkResult<CallSuccessModel>> =
        catDetailsRepo.deleteFavouriteCat(imageId, favId)
}

