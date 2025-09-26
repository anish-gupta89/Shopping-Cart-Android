package com.ag.shoping.repository

import com.ag.shoping.network.RetrofitClient
import com.ag.shoping.network.model.ExampleRequest
import com.ag.shoping.network.model.ExampleResponse
// import retrofit2.Response // No longer needed here

class ExampleRepository {

    suspend fun postExampleData(request: ExampleRequest): ExampleResponse { // Return ExampleResponse directly
        return RetrofitClient.instance.postExampleData(request)
    }
}
