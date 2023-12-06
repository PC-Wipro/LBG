package com.lbg.project.domain.usecase.catsDetail

import com.lbg.project.data.NetworkResult
import com.lbg.project.domain.mappers.CallSuccessModel
import kotlinx.coroutines.flow.Flow

interface PostFavCatUseCase {
    suspend fun execute(imageId: String): Flow<NetworkResult<CallSuccessModel>>
}