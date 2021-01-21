package com.mirlan.sandbox.domain.interactor

import com.mirlan.sandbox.domain.repo.SalonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetSalonsUseCase(private val repository: SalonsRepository) {
    suspend fun getSalons() = flow {
        emit(repository.getSalons())
    }
}