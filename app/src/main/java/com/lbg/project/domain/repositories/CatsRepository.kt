package com.lbg.project.domain.repositories


import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.mappers.CatDataModel
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
     suspend fun  fetchCats(limit:Int=10): Flow<NetworkResult<List<CatDataModel>>>
     suspend fun fetchFavouriteCats(subId: String): Flow<NetworkResult<List<CatDataModel>>>
     suspend fun fetchTestFavouriteCats(subId: String): Flow<NetworkResult<List<CatDataModel>>>

}