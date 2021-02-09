package com.mirlan.sandbox.domain.repo

import com.mirlan.sandbox.data.service.BaseResponse
import com.mirlan.sandbox.domain.entity.Data
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.utils.Result
import kotlinx.coroutines.flow.Flow

interface SalonsRepository {
    suspend fun getSalons(): BaseResponse<Data>
    suspend fun getSalon(id: Int): BaseResponse<Salon>
    fun getImages(text: String): Flow<Result<Data>>
}