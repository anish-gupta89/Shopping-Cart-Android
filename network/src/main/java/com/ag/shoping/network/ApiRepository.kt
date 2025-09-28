package com.ag.shoping.network

import com.ag.shoping.network.model.AllProductsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository class to handle API calls
 * This class acts as a single source of truth for network operations
 */
class ApiRepository {

    private val apiService = RetrofitClient.apiService

    /**
     * Example function to get user data
     */
    suspend fun getUser(userId: Int): NetworkResult<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.getUser(userId)
            }
        }
    }

    /**
     * Example function to create a new user
     */
    suspend fun createUser(user: Any): NetworkResult<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.createUser(user)
            }
        }
    }

    /**
     * Example function to update user data
     */
    suspend fun updateUser(userId: Int, user: Any): NetworkResult<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.updateUser(userId, user)
            }
        }
    }

    /**
     * Example function to delete a user
     */
    suspend fun deleteUser(userId: Int): NetworkResult<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.deleteUser(userId)
            }
        }
    }

    /**
     * Example function to get users with pagination
     */
    suspend fun getUsers(page: Int = 1, limit: Int = 10): NetworkResult<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.getUsers(page, limit)
            }
        }
    }

    /**
     * Example function for user login
     */
    suspend fun login(email: String, password: String): NetworkResult<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.login(email, password)
            }
        }
    }

    /**
     * Example function for file upload
     */
    suspend fun uploadFile(
        description: String,
        file: okhttp3.MultipartBody.Part,
    ): NetworkResult<Any> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.uploadFile(description, file)
            }
        }
    }


    /**
     * Example function to get user data
     */
    suspend fun getProducts(): NetworkResult<AllProductsResponse> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                apiService.getProductsList()
            }
        }
    }
    
}