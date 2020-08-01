package com.mirlan.sandbox.domain.interactor

import com.mirlan.sandbox.domain.repo.SalonsRepository

class GetSalonUseCase(private val repository: SalonsRepository) {
    suspend fun getSalon(id: Int) = repository.getSalon(id)
}