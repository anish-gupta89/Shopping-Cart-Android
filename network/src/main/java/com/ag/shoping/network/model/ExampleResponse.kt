package com.ag.shoping.network.model

data class ExampleResponse(
    val status: String,
    val message: String,
    val data: ResponseData? // Optional data field
)

data class ResponseData(
    val id: Int,
    val value: String
)
