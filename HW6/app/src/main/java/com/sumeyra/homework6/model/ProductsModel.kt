package com.sumeyra.homework6.model

data class ProductsModel(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)