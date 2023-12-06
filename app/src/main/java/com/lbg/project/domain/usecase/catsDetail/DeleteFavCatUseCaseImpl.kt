package com.lbg.project.domain.usecase.catsDetail

import com.lbg.project.data.NetworkResult
import com.lbg.project.domain.mappers.CallSuccessModel
import com.lbg.project.domain.mappers.mapSuccessData
import com.lbg.project.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteFavCatUseCaseImpl(private val catDetailsRepo: CatDetailsRepository):
    DeleteFavCatUseCase {
    override suspend fun execute(imageId: String, favId: Int): Flow<NetworkResult<CallSuccessModel>> = flow{
        catDetailsRepo.deleteFavouriteCat(imageId, favId).collect{ response->
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

