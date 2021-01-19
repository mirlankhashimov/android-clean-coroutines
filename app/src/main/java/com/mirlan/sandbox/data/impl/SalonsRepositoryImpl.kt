package com.mirlan.sandbox.data.impl

import com.mirlan.sandbox.data.service.Api
import com.mirlan.sandbox.data.service.BaseResponse
import com.mirlan.sandbox.domain.entity.Data
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.domain.repo.SalonsRepository

class SalonsRepositoryImpl(private val api: Api) : SalonsRepository {
    override suspend fun getSalons() = api.getSalons()
    override suspend fun getSalon(id: Int): BaseResponse<Salon> = api.getSalon(id)
}