package com.lbg.project.domain.usecase

import com.lbg.project.data.NetworkResult
import com.lbg.project.domain.mappers.CatDataModel
import com.lbg.project.domain.mappers.mapFavCatsDataItems
import com.lbg.project.domain.repositories.CatsRepository
import com.lbg.project.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavCatsUseCase(private val catRepo: CatsRepository) {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>> = flow {
        catRepo.fetchFavouriteCats(Constants.SUB_ID).collect{ favCat->
            when (favCat) {
                is NetworkResult.Success -> {
                    val catDataList = favCat.data?.map { cat ->
                        cat.mapFavCatsDataItems()
                    }
                    emit(NetworkResult.Success(catDataList))
                }
                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(favCat.message))
                }
                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}

