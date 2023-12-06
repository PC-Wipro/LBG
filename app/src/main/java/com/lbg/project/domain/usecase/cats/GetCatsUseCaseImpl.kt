package com.lbg.project.domain.usecase.cats

import com.lbg.project.data.NetworkResult
import com.lbg.project.domain.mappers.CatDataModel
import com.lbg.project.domain.mappers.mapCatsDataItems
import com.lbg.project.domain.repositories.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCatsUseCaseImpl(private val catsRepo: CatsRepository): GetCatsUseCase {
    override suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>> = flow {
        catsRepo.fetchCats().collect { catResponse ->
            when (catResponse) {
                is NetworkResult.Success -> {
                    val catDataList = catResponse.data?.map { cat ->
                        cat.mapCatsDataItems()
                    }
                    emit(NetworkResult.Success(catDataList))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(catResponse.message))
                }

                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}

