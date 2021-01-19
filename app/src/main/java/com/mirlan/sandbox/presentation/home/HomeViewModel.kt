package com.mirlan.sandbox.presentation.home

import androidx.lifecycle.*
import com.mirlan.sandbox.core.BaseViewModel
import com.mirlan.sandbox.data.service.BaseResponse
import com.mirlan.sandbox.data.vo.Resource
import com.mirlan.sandbox.domain.entity.Data
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.domain.interactor.GetSalonUseCase
import com.mirlan.sandbox.domain.interactor.GetSalonsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getSalonsUseCase: GetSalonsUseCase,
    private val getSalonUseCase: GetSalonUseCase
) : BaseViewModel() {

    private val _salons = MutableLiveData<Resource<BaseResponse<Data>>>()
    val salons: LiveData<Resource<BaseResponse<Data>>> = _salons

    private val _salon = MutableLiveData<Resource<BaseResponse<Salon>>>()
    val salon: LiveData<Resource<BaseResponse<Salon>>> = _salon

    private val _uiState = MutableStateFlow<Resource<BaseResponse<Data>>>(Resource.loading())
    val uiState: StateFlow<Resource<BaseResponse<Data>>> = _uiState

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            delay(1000)
            getSalonsUseCase.getSalons().collect {
                _uiState.value = Resource.success(it)
            }
        }

    }

    fun loadSalon(id: Int) {
        _salon.value = Resource.loading()
        viewModelScope.launch {
            //_salon.value = Resource.success(getSalonUseCase.getSalon(id))
        }
    }

    fun openDetail() {
        //router.navigateTo(screens.detail(1))
    }

    fun exit2() {
        router.backTo(screens.home())
    }

}