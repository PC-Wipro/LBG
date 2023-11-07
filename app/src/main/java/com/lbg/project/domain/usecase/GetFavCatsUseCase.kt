package com.lbg.project.domain.usecase

import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.mappers.CatDataModel
import com.lbg.project.domain.repositories.CatsRepository
import com.lbg.project.utils.Constants
import kotlinx.coroutines.flow.Flow

class GetFavCatsUseCase(private val catDetailsRepo: CatsRepository) {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>> {
        return catDetailsRepo.fetchFavouriteCats(
            Constants.SUB_ID
        )
    }
}

