package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.update_cart.request.Product
import com.palash.retrofitwithhilt_mvvm.models.update_cart.response.UpdateCartResponse
import com.palash.retrofitwithhilt_mvvm.repositories.UpdateCartRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCartViewModel @Inject constructor(private val updateCartRepository: UpdateCartRepository) :
    ViewModel() {
    val updateCartLiveData: LiveData<NetworkResult<UpdateCartResponse>> get() = updateCartRepository.updateCartLiveData
    fun updateCart(updateCartId: Int, product: Product) {
        viewModelScope.launch {
            updateCartRepository.updateCart(updateCartId, product)
        }
    }
}