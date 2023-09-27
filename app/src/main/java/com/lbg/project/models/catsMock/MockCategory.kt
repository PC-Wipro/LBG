package com.lbg.project.models.catsMock

import com.lbg.project.models.Category

data class MockCategory(
    val id: Int =1,
    val name: String="Cats"
)
fun toResponseCatCategory(mockCategory: MockCategory): Category {
    return Category(mockCategory.id,mockCategory.name)
}