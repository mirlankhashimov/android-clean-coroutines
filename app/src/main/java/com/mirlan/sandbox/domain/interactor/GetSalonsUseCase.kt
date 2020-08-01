package com.mirlan.sandbox.domain.interactor

import com.mirlan.sandbox.domain.repo.SalonsRepository

class GetSalonsUseCase(private val repository: SalonsRepository) {
    suspend fun getSalons() = repository.getSalons()
}