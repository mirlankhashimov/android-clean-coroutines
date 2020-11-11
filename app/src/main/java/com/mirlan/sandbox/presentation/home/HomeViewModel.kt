package com.mirlan.sandbox.presentation.home

import androidx.lifecycle.*
import com.mirlan.sandbox.data.service.BaseResponse
import com.mirlan.sandbox.data.vo.Resource
import com.mirlan.sandbox.domain.entity.Data
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.domain.interactor.GetSalonUseCase
import com.mirlan.sandbox.domain.interactor.GetSalonsUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getSalonsUseCase: GetSalonsUseCase,
    private val getSalonUseCase: GetSalonUseCase
) : ViewModel() {

    private val _salons = MutableLiveData<Resource<BaseResponse<Data>>>()
    val salons: LiveData<Resource<BaseResponse<Data>>> = _salons

    private val _salon = MutableLiveData<Resource<BaseResponse<Salon>>>()
    val salon: LiveData<Resource<BaseResponse<Salon>>> = _salon

    init {
        load()
    }

    private fun load() {
        _salons.value = Resource.loading()
        viewModelScope.launch {
            //_salons.value = Resource.success(getSalonsUseCase.getSalons())
        }
    }

    fun loadSalon(id: Int) {
        _salon.value = Resource.loading()
        viewModelScope.launch {
            //_salon.value = Resource.success(getSalonUseCase.getSalon(id))
        }
    }

}