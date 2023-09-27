package com.lbg.project.models.catsMock

import com.lbg.project.models.Breed
import com.lbg.project.models.Category
import com.lbg.project.models.CatsDataModel
import retrofit2.Response

data class MocksCatsDataModel(
    val breeds: List<Breed> = listOf(toResponseCatBread(MockBreed()),toResponseCatBread(MockBreed())),
    val categories: List<Category> = listOf(toResponseCatCategory(MockCategory()),toResponseCatCategory(MockCategory(25,"black"))),
    val height: Int = 250,
    val id: String = "IDCat1",
    val url: String = "https://google.com/",
    val width: Int = 250
)

fun toResponseCats(mocksCatsDataModel: MocksCatsDataModel): Response<List<CatsDataModel>> {
    return Response.success(
        listOf(
            CatsDataModel(
                mocksCatsDataModel.breeds,
                mocksCatsDataModel.categories,
                mocksCatsDataModel.height,
                mocksCatsDataModel.id,
                mocksCatsDataModel.url,
                mocksCatsDataModel.width
            )
        )
    )
}