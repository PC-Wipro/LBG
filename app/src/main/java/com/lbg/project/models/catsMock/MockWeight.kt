package com.lbg.project.models.catsMock

import com.lbg.project.models.Weight

data class MockWeight(
    val imperial: String = "23",
    val metric: String = "25"
)

fun toResponseCatBreedWeight(mockWeight: MockWeight): Weight {
    return Weight(mockWeight.imperial, mockWeight.metric)
}