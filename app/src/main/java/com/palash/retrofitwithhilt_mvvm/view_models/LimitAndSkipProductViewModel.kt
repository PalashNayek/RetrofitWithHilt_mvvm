package com.palash.retrofitwithhilt_mvvm.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithhilt_mvvm.models.limit_skip_product.LimitAndSkipProductResponse
import com.palash.retrofitwithhilt_mvvm.repositories.LimitAndSkipProductRepository
import com.palash.retrofitwithhilt_mvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LimitAndSkipProductViewModel @Inject constructor(private val limitAndSkipProductRepository: LimitAndSkipProductRepository) : ViewModel() {

    val limitAndSkipProductLiveData : LiveData<NetworkResult<LimitAndSkipProductResponse>> get() = limitAndSkipProductRepository.limitAndSkipProductData
    fun limitAndSkipProduct(limit: Int, skip: Int, select: CharSequence){
        viewModelScope.launch {
            limitAndSkipProductRepository.limitAndSkip(limit, skip, select)
        }
    }
}