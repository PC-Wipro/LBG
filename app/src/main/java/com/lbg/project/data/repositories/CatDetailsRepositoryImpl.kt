package com.lbg.project.data.repositories


import com.lbg.project.data.NetworkResult
import com.lbg.project.data.models.SuccessResponse
import com.lbg.project.data.models.catDetails.PostFavCatModel
import com.lbg.project.data.services.catsDetail.CatDetailsApiServiceHelper
import com.lbg.project.data.services.catsDetail.CatsDetailsDatabaseHelper
import com.lbg.project.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


class CatDetailsRepositoryImpl(
    private val catDetailsApiService: CatDetailsApiServiceHelper,
    private val catsDetailsDatabaseHelper: CatsDetailsDatabaseHelper
) : CatDetailsRepository {

    override suspend fun postFavouriteCat(favCat: PostFavCatModel) =
        flow<NetworkResult<SuccessResponse>> {
            emit(NetworkResult.Loading())
            with(catDetailsApiService.postFavouriteCat(favCat)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                    this.body()?.id?.let {
                        catsDetailsDatabaseHelper.insertFavCatImageRelation(
                            it, favCat.imageId
                        )
                    }
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }
        }.catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }

    override suspend fun deleteFavouriteCat(imageId: String, favouriteId: Int) =
        flow<NetworkResult<SuccessResponse>> {
            emit(NetworkResult.Loading())
            with(catDetailsApiService.deleteFavouriteCat(favouriteId)) {
                if (isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                    catsDetailsDatabaseHelper.deleteFavImage(imageId)
                } else {
                    emit(NetworkResult.Error(this.errorBody().toString()))
                }
            }

        }.catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }


    override suspend fun isFavourite(imageId: String) = flow {
        emit(catsDetailsDatabaseHelper.isFavourite(imageId))
    }


}



