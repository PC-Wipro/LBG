package com.lbg.project.models

data class CatsDataModel(
    val breeds: List<Breed>,
    val categories: List<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)