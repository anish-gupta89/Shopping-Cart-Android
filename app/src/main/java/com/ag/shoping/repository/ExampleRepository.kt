package com.ag.shoping.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.shoping.network.ApiRepository
import com.ag.shoping.network.NetworkResult
import com.ag.shoping.network.model.AllProductsResponse
import kotlinx.coroutines.launch

// import retrofit2.Response // No longer needed here

class ExampleRepository: ViewModel() {

    private val _productList = MutableLiveData<AllProductsResponse>()
    val productList: LiveData<AllProductsResponse> get()  = _productList


    fun getProductList() {
        viewModelScope.launch {
            when(val result  = ApiRepository().getProducts()){
               is NetworkResult.Success -> {
                   _productList.value =  result.data
                }
                is NetworkResult.Error ->{

                }
                else -> {
                    println("Some exception occurred, please try again.")
                }
            }
        }
    }
}
