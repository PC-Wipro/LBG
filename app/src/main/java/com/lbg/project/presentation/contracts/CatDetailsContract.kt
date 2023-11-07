package com.lbg.project.presentation.contracts


import com.lbg.project.data.models.mappers.CallSuccessModel


class CatDetailsContract {
    data class State(
        val postFavCatSuccess: CallSuccessModel?,
        val deleteFavCatSuccess: CallSuccessModel?
    )

}