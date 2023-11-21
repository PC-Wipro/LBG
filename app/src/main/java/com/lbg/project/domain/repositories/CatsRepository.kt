package com.lbg.project.domain.repositories


import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.catData.CatResponse
import com.lbg.project.data.models.catData.FavouriteCatsItem
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
     suspend fun  fetchCats(limit:Int=10): Flow<NetworkResult<List<CatResponse>>>
     suspend fun fetchFavouriteCats(subId: String): Flow<NetworkResult<List<FavouriteCatsItem>>>
     suspend fun fetchTestFavouriteCats(subId: String): Flow<NetworkResult<List<FavouriteCatsItem>>>

}