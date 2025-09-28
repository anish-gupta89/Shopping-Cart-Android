package com.ag.shoping.network

import com.ag.shoping.network.model.AllProductsResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * API Interface for Retrofit
 * Define your API endpoints here
 */
interface ApiInterface {

    // Example GET request
    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): Response<Any>

    @GET("products")
    suspend fun getProductsList(): Response<AllProductsResponse>

    // Example POST request
    @POST("users")
    suspend fun createUser(@Body user: Any): Response<Any>

    // Example PUT request
    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") userId: Int,
        @Body user: Any,
    ): Response<Any>

    // Example DELETE request
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") userId: Int): Response<Any>

    // Example GET request with query parameters
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
    ): Response<Any>

    // Example POST request with form data
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<Any>

    // Example multipart file upload
    @Multipart
    @POST("upload")
    suspend fun uploadFile(
        @Part("description") description: String,
        @Part file: okhttp3.MultipartBody.Part,
    ): Response<Any>
}