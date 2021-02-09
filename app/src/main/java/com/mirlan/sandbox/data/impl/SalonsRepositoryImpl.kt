package com.mirlan.sandbox.data.impl

import com.mirlan.sandbox.data.service.Api
import com.mirlan.sandbox.data.service.BaseResponse
import com.mirlan.sandbox.domain.entity.Data
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.domain.repo.SalonsRepository
import com.mirlan.sandbox.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class SalonsRepositoryImpl(private val api: Api) : SalonsRepository {
    override suspend fun getSalons() = api.getSalons()
    override suspend fun getSalon(id: Int): BaseResponse<Salon> = api.getSalon(id)
    override fun getImages(text: String): Flow<Result<Data>> = flow {

        emit(Result.Loading)

        val images = api.getSalons().data

        emit(Result.Success(images!!))
    }.catch { e ->
        emit(Result.Error(e as Exception))
    }
}