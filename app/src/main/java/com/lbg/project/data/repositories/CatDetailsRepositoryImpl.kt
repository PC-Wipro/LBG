package com.lbg.project.data.repositories


import com.lbg.project.data.NetworkResult
import com.lbg.project.data.database.LBGDatabase
import com.lbg.project.data.database.entities.FavImageEntity
import com.lbg.project.data.models.SuccessResponse
import com.lbg.project.data.models.catDetails.PostFavCatModel
import com.lbg.project.data.services.CatsService
import com.lbg.project.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class CatDetailsRepositoryImpl(private val catsService: CatsService, private val db: LBGDatabase) : CatDetailsRepository {


    override suspend fun postFavouriteCat(favCat: PostFavCatModel) =
        flow<NetworkResult<SuccessResponse>> {
            emit(NetworkResult.Loading())
            with(catsService.postFavouriteCat(favCat)) {
                if (isSuccessful) {
                    this.body()?.id?.let { FavImageEntity(it, favCat.imageId) }
                        ?.let { db.favImageDao().insertFavCatImageRelation(it) }
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }

    override suspend fun deleteFavouriteCat(imageId: String, favouriteId: Int) =
        flow<NetworkResult<SuccessResponse>> {
            emit(NetworkResult.Loading())
            with(catsService.deleteFavouriteCat(favouriteId)) {
                if (isSuccessful) {
                    db.favImageDao().deleteFavImage(imageId)
                    emit(NetworkResult.Success(this.body()))
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }

        }.flowOn(Dispatchers.IO)
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }


    override suspend fun isFavourite(imageId: String) = flow {
        emit(db.favImageDao().getFavId(imageId))
    }



}



