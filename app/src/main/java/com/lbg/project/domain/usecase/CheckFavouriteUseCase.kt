package com.lbg.project.domain.usecase

import com.lbg.project.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow

class CheckFavouriteUseCase(private val catDetailsRepo: CatDetailsRepository) {
    suspend fun execute(imageId:String): Flow<Int?> = catDetailsRepo.isFavourite(imageId)
}

