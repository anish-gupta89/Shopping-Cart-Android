package com.ag.shoping.network

import com.ag.shoping.network.model.ExampleRequest
import com.ag.shoping.network.model.ExampleResponse
// import retrofit2.Response // No longer needed here for the return type
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // Define your API endpoints here

    @POST("example/endpoint")
    suspend fun postExampleData(@Body request: ExampleRequest): ExampleResponse // Return ExampleResponse directly
}
