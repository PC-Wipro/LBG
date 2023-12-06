package com.lbg.project.domain.usecase.catsDetail

import com.lbg.project.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow

class CheckFavouriteUseCaseImpl(private val catDetailsRepo: CatDetailsRepository): CheckFavUseCase {
    override suspend fun execute(imageId:String): Flow<Int?> = catDetailsRepo.isFavourite(imageId)
}

