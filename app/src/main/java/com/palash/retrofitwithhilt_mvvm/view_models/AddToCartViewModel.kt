package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.add_new_cart.AddNewCartRequest
import com.palash.retrofitwithhilt_mvvm.models.add_new_cart.response.AddNewCartResponse
import com.palash.retrofitwithhilt_mvvm.repositories.AddToCartRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToCartViewModel @Inject constructor(private val addToCartRepository: AddToCartRepository): ViewModel() {

    val addToCardLiveData: LiveData<NetworkResult<AddNewCartResponse>> get() = addToCartRepository.addToCartLiveData
    fun addToCart(addNewCartRequest: AddNewCartRequest){
        viewModelScope.launch {
            addToCartRepository.addToCard(addNewCartRequest)
        }
    }
}