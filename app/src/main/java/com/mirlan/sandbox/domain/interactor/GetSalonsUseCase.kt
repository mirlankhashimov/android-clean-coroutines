package com.mirlan.sandbox.domain.interactor

import com.mirlan.sandbox.domain.repo.SalonsRepository
import kotlinx.coroutines.flow.flow

class GetSalonsUseCase(private val repository: SalonsRepository) {
    suspend fun getSalons() = flow {
        emit(repository.getSalons())
    }
}