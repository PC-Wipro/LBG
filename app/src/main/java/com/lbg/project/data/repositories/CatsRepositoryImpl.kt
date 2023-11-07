package com.lbg.project.data.repositories

import com.lbg.project.data.NetworkResult
import com.lbg.project.data.database.LBGDatabase
import com.lbg.project.data.database.entities.FavImageEntity
import com.lbg.project.data.models.catData.CatResponse
import com.lbg.project.data.models.catData.FavouriteCatsItem
import com.lbg.project.data.models.mappers.CatDataModel
import com.lbg.project.data.services.CatsService
import com.lbg.project.domain.repositories.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CatsRepositoryImpl(private val catsService: CatsService, private val db: LBGDatabase) :
    CatsRepository {
    override suspend fun fetchCats(limit: Int) = flow<NetworkResult<List<CatDataModel>>> {
        emit(NetworkResult.Loading())
        with(catsService.fetchCatsImages(limit)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()?.let { mapCatsDataItems(it) }))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }

    override suspend fun fetchFavouriteCats(subId: String) =
        flow<NetworkResult<List<CatDataModel>>> {
            emit(NetworkResult.Loading())
            with(catsService.fetchFavouriteCats(subId)) {
                if (isSuccessful) {
                    val storeInfo = this.body()?.map {
                        FavImageEntity(
                            favouriteId = it.id,
                            imageId = it.imageId
                        )
                    }
                    if (storeInfo != null) {
                        db.favImageDao().insertFavCatImageRelation(storeInfo)
                    }
                    emit(NetworkResult.Success(this.body()?.let { mapFavCatsDataItems(it) }))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }

    override suspend fun fetchTestFavouriteCats(subId: String)=
        flow<NetworkResult<List<CatDataModel>>> {
            emit(NetworkResult.Loading())
            with(catsService.fetchFavouriteCats(subId)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()?.let { mapFavCatsDataItems(it) }))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }


    private fun mapCatsDataItems(cats: List<CatResponse>): List<CatDataModel> {
        return cats.map { cat ->
            CatDataModel(
                imageId = cat.id,
                url = cat.url
            )
        }
    }

    private fun mapFavCatsDataItems(cats: List<FavouriteCatsItem>): List<CatDataModel> {
        return cats.map { cat ->
            CatDataModel(
                favId = cat.id,
                url = cat.image.url,
                imageId = cat.imageId,
            )
        }


    }
}