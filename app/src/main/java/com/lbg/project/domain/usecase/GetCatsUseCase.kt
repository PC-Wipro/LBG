package com.lbg.project.domain.usecase

import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.mappers.CatDataModel
import com.lbg.project.domain.repositories.CatsRepository
import kotlinx.coroutines.flow.Flow

class GetCatsUseCase(private val catsRepo: CatsRepository) {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>> = catsRepo.fetchCats()
    suspend fun execute(subId:String): Flow<NetworkResult<List<CatDataModel>>> = catsRepo.fetchFavouriteCats(subId)
}

